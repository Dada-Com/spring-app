package com.dada.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datamodels.ContactResponseType;

/////// Controller is API layer this concern taking data from user and giving it back to api
// get the list of movies the return with the http status OK
@RestController
@RequestMapping("/api/contactresponse")
@CrossOrigin(origins = "*")
public class ContactResponseController {
	@Autowired
	private ContactResponservice contactResponservice;
	
	/*public class DuplicateValueException extends RuntimeException {
	    public DuplicateValueException(String message) {
	        super(message);
	    }
	}*/
  @GetMapping("/allmasala")
  public ResponseEntity<List<ContactResponseType>> getallContactdata() {
	 return new ResponseEntity<List<ContactResponseType>>(contactResponservice.allResponseContact(),HttpStatus.OK);
  }
  //@PathVariable === It convert the ObjectId to id
  @GetMapping("/{contactresid}")
  public ResponseEntity<Optional<ContactResponseType>> getsingleMovie(@PathVariable String contactresid){
	  return new  ResponseEntity<Optional<ContactResponseType>>(contactResponservice.ByResponseContactid(contactresid),HttpStatus.OK);
  }

 @Autowired
 ContactResonpRepo contactResonpRepo;
  @Autowired
  private MongoTemplate mongoTemplate;

  @PostMapping("/contactrestypes")
  public ResponseEntity<String> saveOrUpdate(@RequestBody ContactResponseType contactResponseType) {
      // Check if a document with the same unique value already exists
      Query query = new Query(Criteria.where("contactresid").is(contactResponseType.getContactresid()));
      ContactResponseType existingModel = mongoTemplate.findOne(query, ContactResponseType.class);
      if (existingModel == null) {
          // No existing document, perform the save operation
          mongoTemplate.save(contactResponseType);
          return new  ResponseEntity<String>("Successful",HttpStatus.OK);
      } else {
          // Document with the same unique value already exists, handle accordingly
          // You can throw an exception, log a message, or update the existing document
          // based on your business requirements.
          // For example, you can return a ResponseEntity with an appropriate status code and message:
    	  return new  ResponseEntity<String>("Document with the same unique value already exists",HttpStatus.CONFLICT);
      }
  }

// 
//  @PatchMapping("/update/{contactid}")
//  public ResponseEntity<String> updateAboutType(@PathVariable String contactid,@RequestBody ContactType contactType) {
//      Optional<ContactType> savecontId = contactRepo.findByContactid(contactid);
//      if (savecontId.isPresent()) {
//    	  ContactType contactupdate = savecontId.get();
//    	  contactupdate.setDescrip(contactType.getDescrip());
//    	  contactupdate.setAddress(contactType.getAddress());
//    	  contactupdate.setContactno(contactType.getContactno());
//    	  contactupdate.setEmail(contactType.getEmail());
//    	  contactRepo.save(contactupdate);
//    	  return new  ResponseEntity<String>("Successful update",HttpStatus.OK);
//      } else {
//    	  return new  ResponseEntity<String>("Fail to update",HttpStatus.CONFLICT);
//      }
//     }
//     

  @DeleteMapping("/delete/{contactresid}")
  public ResponseEntity<String> deleteHomeType(@PathVariable String contactresid) {
      Optional<ContactResponseType> savedcontaresponseid = contactResonpRepo.findByContactresid(contactresid);
      if (savedcontaresponseid.isPresent()) {
    	  contactResonpRepo.deleteByContactresid(contactresid);
          return new  ResponseEntity<String>("Deleted",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to Delete",HttpStatus.CONFLICT);
      }
      
  }
}
