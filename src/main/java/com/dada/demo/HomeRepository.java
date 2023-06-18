package com.dada.demo;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import datamodels.HomeType;

@Repository
public interface HomeRepository extends MongoRepository<HomeType, String> {
	Optional<HomeType> findByPid(String pid);
	Optional<HomeType> deleteByPid(String pid);
}
