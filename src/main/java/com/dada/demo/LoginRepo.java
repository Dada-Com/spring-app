package com.dada.demo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import datamodels.LoginType;



public interface LoginRepo extends MongoRepository<LoginType, String> {
	Optional<LoginType> findByUsername(String username);
	Optional<LoginType> deleteByUsername(String username);
}