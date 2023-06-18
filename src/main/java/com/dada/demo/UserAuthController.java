package com.dada.demo;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.annotation.AccessType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
//import java.util.List;
import java.util.Optional;

//import org.springframework.security.core.Authentication;
/*****For Extracting from qurey variable v|v DOWN *****/
import org.bson.Document;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/*****For Extracting from qurey variable ^|^ UP *****/
import datamodels.AdminSecurityType;
import datamodels.LoginType;
//import datamodels.LoginType;
import datamodels.SignupType;
//import datamodels.User;
@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class UserAuthController {
	@Autowired
	private LoginService loginService;
 
    @GetMapping("/user")
    public String getUser() {  
	  return "user";
    }

    @GetMapping("/currentuser")
    public String getLoginUser(Principal principal) {
	  return principal.getName();
    }

    @PostMapping("/login")
    public String getLogin(@RequestBody LoginType loginType ) {
    	Optional<LoginType> savedLoginDetails = loginService.ByUsername(loginType.getUsername());
    	Boolean res = null;
    	String password = null;    	
    	LoginType savedusername = savedLoginDetails.orElse(null);
    	if(savedusername != null) {
    		password = savedusername.getPassword();
           }
         else {        	 
        	 System.out.println("User does not exist ");
          	return "User does not exist";
         }
    	if(password.equals(loginType.getPassword())) {
    		res = true;
    	}
    	else {
    		res = false;
    	}
    	System.out.println("Header Username :"+loginType.getUsername());
    	System.out.println("Header Password :"+loginType.getPassword());
    	System.out.println("Saved password :"+password.toString());
	  return res.toString();
    }

  @Autowired
  SignupRepo signupRepo;
  @Autowired
  AdminSecureRepo adminSecureRepo;
  @Autowired
  private MongoTemplate mongoTemplate;
  /***********     For Signup            ***********/
  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody SignupType signupType) {
      // Check if a document with the same unique value already exists
      Query query = new Query(Criteria.where("username").is(signupType.getUsername()));
      SignupType existingModel = mongoTemplate.findOne(query, SignupType.class);
      // Check if a document with the same unique value already exists ======= adminid
      Query query2 = new Query(Criteria.where("adminid").is(signupType.getAdminid()));
      SignupType existingModelForAdminid = mongoTemplate.findOne(query2, SignupType.class);
      // For checking the admin id already in database for security of admin signup no one can directly signup without knowing adminID
      Query query1 = new Query(Criteria.where("adminid").is(signupType.getAdminid()));
      String adminid = "";
      Document queryDocument = query1.getQueryObject();
      if (queryDocument.containsKey("adminid")) {
          adminid = queryDocument.get("adminid").toString();
      }
      Optional<AdminSecurityType> savedAdminIdOptional = adminSecureRepo.findByAdminid(adminid);
      AdminSecurityType savedAdminId = savedAdminIdOptional.orElse(null);
      if(savedAdminId == null) {
     	  return new ResponseEntity<String>("Admin ID is not registered or Admin ID is incorrect", HttpStatus.CONFLICT);
       }
      if (existingModel == null && existingModelForAdminid == null && savedAdminId != null && savedAdminId.getAdminid().equals(adminid)) {
          // No existing document, perform the save operation
          mongoTemplate.save(signupType);
          return new ResponseEntity<String>("Successful", HttpStatus.CREATED);
      } 
      else if (existingModelForAdminid != null) {
    	  return new ResponseEntity<String>("Admin is already create by using this admin id", HttpStatus.CONFLICT);
	}
      else {
          return new ResponseEntity<String>("username already exists", HttpStatus.CONFLICT);
      }
  }

 
  @PatchMapping("/update/{username}")
  public ResponseEntity<String> updateAboutType(@PathVariable String username,@RequestBody SignupType signupType) {
      Optional<SignupType> saveSignupId = signupRepo.findByUsername(username);
      String username1 = "";
      SignupType savedusername = saveSignupId.orElse(null);
      if(savedusername == null) {
      	 System.out.println("user does not exist");
      	  return new ResponseEntity<String>("user does not exist", HttpStatus.CONFLICT);
        }
      else {
    	  username1 = savedusername.getUsername();
      }
      System.out.println("Saved Username on database :"+savedusername.getUsername());
      System.out.println("Username from Signuptype: "+signupType.getUsername());
      // Check if a document with the same unique value already exists
      if (saveSignupId.isPresent() && username1.equals(signupType.getUsername())) {
    	  SignupType SignupUpdate = saveSignupId.get();
    	  SignupUpdate.setUsername(signupType.getUsername());
    	  SignupUpdate.setPassword(signupType.getPassword());
    	  signupRepo.save(SignupUpdate);
    	  return new  ResponseEntity<String>("Successful update",HttpStatus.OK);
      }
      else if (!username1.equals(signupType.getUsername())) {
    	  return new ResponseEntity<String>("user does not exist", HttpStatus.CONFLICT);
	  }
      else {
    	  return new  ResponseEntity<String>("Fail to update",HttpStatus.CONFLICT);
      }
     }
     

  @DeleteMapping("/delete/{username}")
  public ResponseEntity<String> deleteHomeType(@PathVariable String username) {
      Optional<SignupType> saveSignupId = signupRepo.findByUsername(username);
      if (saveSignupId.isPresent()) {
    	  signupRepo.deleteByUsername(username);
          return new  ResponseEntity<String>("Deleted",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to Delete",HttpStatus.CONFLICT);
      }
      
  }
}