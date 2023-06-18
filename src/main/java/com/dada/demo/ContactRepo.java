package com.dada.demo;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import datamodels.ContactType;


@Repository
public interface ContactRepo extends MongoRepository<ContactType, String> {
	Optional<ContactType> findByContactid(String contactid);
	Optional<ContactType> deleteByContactid(String contactid);
}
