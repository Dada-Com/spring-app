package com.dada.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datamodels.AboutType;

/*
In this class most of business logic for the api
* */
@Service
public class Aboutservice {
	@Autowired
   private  AboutRepo aboutRepo;
	
	public List<AboutType> allinfo() {
		return aboutRepo.findAll();	  
 }
	// Optional type = findById method maybe not find any movie , movie is not exist it should return null  
	/*public Optional<Movie> singleMovie(ObjectId id) {
		return movieRepository.findById(id);
	}*/
	
	public Optional<AboutType> ByAid(String aid) {
		return aboutRepo.findByAid(aid);
	}
	
	
}
