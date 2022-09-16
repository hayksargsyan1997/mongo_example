package com.example.mongo_example.repository;

import com.example.mongo_example.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
