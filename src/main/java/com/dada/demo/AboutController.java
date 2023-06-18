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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datamodels.AboutType;
import datamodels.HomeType;


/////// Controller is API layer this concern taking data from user and giving it back to api
// get the list of movies the return with the http status OK
@RestController
@RequestMapping("/api/about")
@CrossOrigin(origins = "*")
public class AboutController {
	@Autowired
	private Aboutservice aboutservice;
	
	/*public class DuplicateValueException extends RuntimeException {
	    public DuplicateValueException(String message) {
	        super(message);
	    }
	}*/
  @GetMapping
  public ResponseEntity<List<AboutType>> getalldata() {
	 return new ResponseEntity<List<AboutType>>(aboutservice.allinfo(),HttpStatus.OK);
  }
  //@PathVariable === It convert the ObjectId to id
  @GetMapping("/{aid}")
  public ResponseEntity<Optional<AboutType>> getsingleMovie(@PathVariable String aid){
	  return new  ResponseEntity<Optional<AboutType>>(aboutservice.ByAid(aid),HttpStatus.OK);
  }

 @Autowired
  AboutRepo aboutRepo;
//  
//  @PostMapping("/hometypes")
//  public String createHomeType(@RequestBody HomeType homeType) {
//	  HomeType savedHomeType = homeRepository.insert(homeType);
//	   if (savedHomeType != null) {
//          return "HomeType saved successfully";
//      } else {
//          return "Failed to save HomeType";
//      }
//  }

  @Autowired
  private MongoTemplate mongoTemplate;

  @PostMapping("/abouttypes2")
  public ResponseEntity<String> saveOrUpdate(@RequestBody AboutType aboutType) {
      // Check if a document with the same unique value already exists
      Query query = new Query(Criteria.where("aid").is(aboutType.getAid()));
      HomeType existingModel = mongoTemplate.findOne(query, HomeType.class);
      if (existingModel == null) {
          // No existing document, perform the save operation
          mongoTemplate.save(aboutType);
          return new  ResponseEntity<String>("Successful",HttpStatus.OK);
      } else {
          // Document with the same unique value already exists, handle accordingly
          // You can throw an exception, log a message, or update the existing document
          // based on your business requirements.
          // For example, you can return a ResponseEntity with an appropriate status code and message:
    	  return new  ResponseEntity<String>("Document with the same unique value already exists",HttpStatus.CONFLICT);
      }
  }

 
  @PatchMapping("/update/{aid}")
  public ResponseEntity<String> updateAboutType(@PathVariable String aid,@RequestBody AboutType aboutType) {
      Optional<AboutType> saveAboutId = aboutRepo.findByAid(aid);
      if (saveAboutId.isPresent()) {
    	  AboutType aboutUpdate = saveAboutId.get();
    	  aboutUpdate.setAboutus(aboutType.getAboutus());
    	  aboutUpdate.setEducation(aboutType.getEducation());
    	  aboutUpdate.setSkills(aboutType.getSkills());
    	  aboutRepo.save(aboutUpdate);
    	  return new  ResponseEntity<String>("Successful update",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to update",HttpStatus.CONFLICT);
      }
     }
     

  @DeleteMapping("/delete/{aid}")
  public ResponseEntity<String> deleteHomeType(@PathVariable String aid) {
      Optional<AboutType> savedHomeId = aboutRepo.findByAid(aid);
      if (savedHomeId.isPresent()) {
    	  aboutRepo.deleteByAid(aid);
          return new  ResponseEntity<String>("Deleted",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to Delete",HttpStatus.CONFLICT);
      }
      
  }
}
