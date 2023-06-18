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

import datamodels.HomeType;


/////// Controller is API layer this concern taking data from user and giving it back to api
// get the list of movies the return with the http status OK
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HomeController {
	@Autowired
	private HomeServics homeServics;
	
	/*public class DuplicateValueException extends RuntimeException {
	    public DuplicateValueException(String message) {
	        super(message);
	    }
	}*/
  @GetMapping
  public ResponseEntity<List<HomeType>> getalldata() {
	 return new ResponseEntity<List<HomeType>>(homeServics.allinfo(),HttpStatus.OK);
  }
  //@PathVariable === It convert the ObjectId to id
  @GetMapping("/{pid}")
  public ResponseEntity<Optional<HomeType>> getsingleMovie(@PathVariable String pid){
	  return new  ResponseEntity<Optional<HomeType>>(homeServics.ByPid(pid),HttpStatus.OK);
  }

  @Autowired
  HomeRepository homeRepository;
  
  @PostMapping("/hometypes")
  public String createHomeType(@RequestBody HomeType homeType) {
	  HomeType savedHomeType = homeRepository.insert(homeType);
	   if (savedHomeType != null) {
          return "HomeType saved successfully";
      } else {
          return "Failed to save HomeType";
      }
  }

  @Autowired
  private MongoTemplate mongoTemplate;

  @PostMapping("/hometypes2")
  public ResponseEntity<String> saveOrUpdate(@RequestBody HomeType homeType) {
      // Check if a document with the same unique value already exists
      Query query = new Query(Criteria.where("pid").is(homeType.getPid()));
      HomeType existingModel = mongoTemplate.findOne(query, HomeType.class);
      if (existingModel == null) {
          // No existing document, perform the save operation
          mongoTemplate.save(homeType);
          return new  ResponseEntity<String>("Successful",HttpStatus.OK);
      } else {
          // Document with the same unique value already exists, handle accordingly
          // You can throw an exception, log a message, or update the existing document
          // based on your business requirements.
          // For example, you can return a ResponseEntity with an appropriate status code and message:
    	  return new  ResponseEntity<String>("Document with the same unique value already exists",HttpStatus.CONFLICT);
      }
  }

 
  @PatchMapping("/update/{pid}")
  public ResponseEntity<String> updateHomeType(@PathVariable String pid,@RequestBody HomeType homeType) {
      Optional<HomeType> savedHomeId = homeRepository.findByPid(pid);
      if (savedHomeId.isPresent()) {
    	  HomeType homeupdate = savedHomeId.get();
    	  homeupdate.setName(homeType.getName());
    	  homeupdate.setDescrp(homeType.getDescrp());
    	  homeType.setTlink(homeType.getTlink());
    	  homeType.setLlink(homeType.getLlink());
    	  homeupdate.setGlink(homeType.getGlink());
    	  homeupdate.setImgsrc(homeType.getImgsrc());
    	  homeType.setLogo(homeType.getLogo());
    	  homeRepository.save(homeupdate);
    	  return new  ResponseEntity<String>("Successful update",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to update",HttpStatus.CONFLICT);
      }
     }
     

  @DeleteMapping("/delete/{pid}")
  public ResponseEntity<String> deleteHomeType(@PathVariable String pid) {
      Optional<HomeType> savedHomeId = homeRepository.findByPid(pid);
      if (savedHomeId.isPresent()) {
    	  homeRepository.deleteByPid(pid);
          return new  ResponseEntity<String>("Deleted",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to Delete",HttpStatus.CONFLICT);
      }
      
  }
}
