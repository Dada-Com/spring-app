package com.dada.demo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import datamodels.SignupType;

public interface SignupRepo extends MongoRepository<SignupType, String> {
	Optional<SignupType> findByUsername(String username);
	Optional<SignupType> deleteByUsername(String username);
}