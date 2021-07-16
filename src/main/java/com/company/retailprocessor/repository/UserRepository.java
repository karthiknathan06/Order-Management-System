package com.company.retailprocessor.repository;

import com.company.retailprocessor.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
    void deleteByEmail(String email);
    User findByUserName(String name);
    User findByEmail(String email);
}
