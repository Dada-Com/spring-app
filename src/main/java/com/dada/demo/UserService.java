package com.dada.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import datamodels.User;



@Service
public class UserService {
  
	private List<User> store = new ArrayList<>();
	
	public UserService() {
		store.add(new User(UUID.randomUUID().toString(),"dada","dada"));
		store.add(new User(UUID.randomUUID().toString(),"dada1","dad1"));
		store.add(new User(UUID.randomUUID().toString(),"dada2","dad2"));
	}
	
	public List<User> getUser(){
		return this.store;
	}
}
