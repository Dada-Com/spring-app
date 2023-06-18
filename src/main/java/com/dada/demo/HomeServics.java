package com.dada.demo;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import datamodels.HomeType;

/*
 In this class most of business logic for the api
 * */
@Service
public class HomeServics {
	@Autowired
    private  HomeRepository homeRepository;
	
	public List<HomeType> allinfo() {
		return homeRepository.findAll();	  
  }
	// Optional type = findById method maybe not find any movie , movie is not exist it should return null  
	/*public Optional<Movie> singleMovie(ObjectId id) {
		return movieRepository.findById(id);
	}*/
	
	public Optional<HomeType> ByPid(String pid) {
		return homeRepository.findByPid(pid);
	}
	
	
}
