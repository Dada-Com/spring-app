package com.dada.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import datamodels.ContactResponseType;
/*
In this class most of business logic for the api
* */
@Service
public class ContactResponservice {
	@Autowired
   private ContactResonpRepo contactResonpRepo;
	
	public List<ContactResponseType> allResponseContact() {
		return contactResonpRepo.findAll();	  
 }
	// Optional type = findById method maybe not find any movie , movie is not exist it should return null  
	/*public Optional<Movie> singleMovie(ObjectId id) {
		return movieRepository.findById(id);
	}*/
	
	public Optional<ContactResponseType> ByResponseContactid(String contactresid) {
		return contactResonpRepo.findByContactresid(contactresid);
	}
	
}
