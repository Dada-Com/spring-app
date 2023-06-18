package com.dada.demo;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datamodels.LoginType;
@Service
public class LoginService {
	@Autowired
	   private  LoginRepo authRepo;
		
	
		public Optional<LoginType> ByUsername(String aid) {
			return authRepo.findByUsername(aid);	
			}
}
