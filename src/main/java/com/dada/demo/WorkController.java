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

import datamodels.WorkType;


/////// Controller is API layer this concern taking data from user and giving it back to api
// get the list of movies the return with the http status OK
@RestController
@RequestMapping("/api/work")
@CrossOrigin(origins = "*")
public class WorkController {
	@Autowired
	private WorkService  workservice;
	
	/*public class DuplicateValueException extends RuntimeException {
	    public DuplicateValueException(String message) {
	        super(message);
	    }
	}*/
  @GetMapping("/all")
  public ResponseEntity<List<WorkType>> getalldata() {
	 return new ResponseEntity<List<WorkType>>(workservice.allWork(),HttpStatus.OK);
  }
  //@PathVariable === It convert the ObjectId to id
  @GetMapping("/{workno}")
  public ResponseEntity<Optional<WorkType>> getsingleMovie(@PathVariable String workno){
	  return new  ResponseEntity<Optional<WorkType>>(workservice.ByWorkNo(workno),HttpStatus.OK);
  }

 @Autowired
 WorkRepo workRepo;
  @Autowired
  private MongoTemplate mongoTemplate;

  @PostMapping("/worktypes")
  public ResponseEntity<String> saveOrUpdate(@RequestBody WorkType workType) {
      // Check if a document with the same unique value already exists
      Query query = new Query(Criteria.where("workno").is(workType.getWorkno()));
      WorkType existingModel = mongoTemplate.findOne(query, WorkType.class);
      if (existingModel == null) {
          // No existing document, perform the save operation
          mongoTemplate.save(workType);
          return new  ResponseEntity<String>("Successful",HttpStatus.OK);
      } else {
          // Document with the same unique value already exists, handle accordingly
          // You can throw an exception, log a message, or update the existing document
          // based on your business requirements.
          // For example, you can return a ResponseEntity with an appropriate status code and message:
    	  return new  ResponseEntity<String>("Document with the same unique value already exists",HttpStatus.CONFLICT);
      }
  }

 
  @PatchMapping("/update/{workno}")
  public ResponseEntity<String> updateAboutType(@PathVariable String workno,@RequestBody WorkType workType) {
      Optional<WorkType> saveAboutId = workRepo.findByWorkno(workno);
      if (saveAboutId.isPresent()) {
    	  WorkType workupdate = saveAboutId.get();
    	  workupdate.setTittle(workType.getTittle());
    	  workupdate.setDescrip(workType.getDescrip());
    	  workupdate.setImgsrc(workType.getImgsrc());
    	  workupdate.setGithublink(workType.getGithublink());
    	  workRepo.save(workupdate);
    	  return new  ResponseEntity<String>("Successful update",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to update",HttpStatus.CONFLICT);
      }
     }
     

  @DeleteMapping("/delete/{workno}")
  public ResponseEntity<String> deleteHomeType(@PathVariable String workno) {
      Optional<WorkType> savedworkno = workRepo.findByWorkno(workno);
      if (savedworkno.isPresent()) {
    	  workRepo.deleteByWorkno(workno);
          return new  ResponseEntity<String>("Deleted",HttpStatus.OK);
      } else {
    	  return new  ResponseEntity<String>("Fail to Delete",HttpStatus.CONFLICT);
      }
      
  }
}
