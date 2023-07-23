package com.example.demo_spring_with_mongo.repositories;


import com.example.demo_spring_with_mongo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
}