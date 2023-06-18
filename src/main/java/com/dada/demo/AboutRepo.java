package com.dada.demo;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import datamodels.AboutType;

@Repository
public interface AboutRepo extends MongoRepository<AboutType, String> {
	Optional<AboutType> findByAid(String aid);
	Optional<AboutType> deleteByAid(String aid);
}
