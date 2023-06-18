package com.dada.demo;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import datamodels.WorkType;





@Repository
public interface WorkRepo extends MongoRepository<WorkType, String> {
	Optional<WorkType> findByWorkno(String workno);
	Optional<WorkType> deleteByWorkno(String workno);
}
