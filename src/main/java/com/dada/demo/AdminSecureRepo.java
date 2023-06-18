package com.dada.demo;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import datamodels.AdminSecurityType;

public interface AdminSecureRepo extends MongoRepository<AdminSecurityType, String> {
	Optional<AdminSecurityType> findByAdminid(String adminid);
	Optional<AdminSecurityType> deleteByAdminid(String adminid);
}