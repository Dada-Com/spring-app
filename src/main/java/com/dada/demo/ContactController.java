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

import datamodels.ContactType;


/////// Controller is API layer this concern taking data from user and giving it back to api
// get the list of movies the return with the http status OK
@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {
	@Autowired
	private ContactCservice contactCservice;
	
	/*public class DuplicateValueException extends RuntimeException {
	    public DuplicateValueException(String message) {
	        super(message);
	    }
	}*/
  @GetMapping
  public ResponseEntity<List<ContactType>> getalldata() {
	 return new ResponseEntity<List<ContactType>>(contactCservice.allContact(),HttpStatus.OK);
  }
  //@PathVariable === It convert the ObjectId to id
  @GetMapping("/{conid}")
  public ResponseEntity<Optional<ContactType>> getsingleMovie(@PathVariable String conid){
	  return new  ResponseEntity<Optional<ContactType>>(contactCservice.ByContactNo(conid),HttpStatus.OK);
  }

 @Autowired
 ContactRepo contactRepo;
  @Autowired
  private MongoTemplate mongoTemplate;

  @PostMapping("/contacttypes")
  public ResponseEntity<String> saveOrUpdate(@RequestBody ContactType contactType) {
      // Check if a document with the same unique value already exists
      Query query = new Query(Criteria.where("contactid").is(contactType.getContactid()));
      ContactType existingModel = mongoTemplate.findOne(query, ContactType.class);
      if (existingModel == null) {
          // No existing document, perform the save operation
          mongoTemplate.save(contactType);
          return new  ResponseEntity<String>("Successful",HttpStatus.OK);
      } else {
          // Document with the same unique value already exists, handle accordingly
          // You can throw an exception, log a message, or update the existing document
          // based on your business requirements.
          // For example, you can return a ResponseEntity with an appropriate status code and message:
    	  return new  ResponseEntity<String>("Document with the same unique value already exists",HttpStatus.CONFLICT);
      }
  }

 
  @PatchMapping("/update/{contactid}")
  public ResponseEntity<String> updateAboutType(@PathVariable String contactid,@RequestBody ContactType contactType) {
      Optional<ContactType> savecontId = contactRepo.findByContactid(contactid);
      if (savecontId.isPresent()) {
    	  ContactType contactupdate = savecontId.get();
    	  contactupdate.setDescrip(contactType.getDescrip());
    	  contactupdate.setAddress(contactType.getAddress());
    	  contactupdate.setContactno(contactType.getContactno());
    	  contactupdate.setEmail(contactType.getEmail());
    	  contactRepo.save(contactupdate);
    	  return new  ResponseEntity<String>("Successful update",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to update",HttpStatus.CONFLICT);
      }
     }
     

  @DeleteMapping("/delete/{contactid}")
  public ResponseEntity<String> deleteHomeType(@PathVariable String contactid) {
      Optional<ContactType> savedcontaid = contactRepo.findByContactid(contactid);
      if (savedcontaid.isPresent()) {
    	  contactRepo.deleteByContactid(contactid);
          return new  ResponseEntity<String>("Deleted",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to Delete",HttpStatus.CONFLICT);
      }
      
  }
}
