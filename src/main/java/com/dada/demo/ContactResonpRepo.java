package com.dada.demo;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import datamodels.ContactResponseType;


@Repository
public interface ContactResonpRepo extends MongoRepository<ContactResponseType, String> {
	Optional<ContactResponseType> findByContactresid(String contactresid);
	Optional<ContactResponseType> deleteByContactresid(String contactresid);
}
