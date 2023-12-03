package com.policy.repository;

import com.policy.entity.PolicyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PolicyRepository extends MongoRepository<PolicyEntity,String> {
    @Query(value = "{}", count = true)
    public Long countAllDocuments();
}