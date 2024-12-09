package com.vaibhavsinghsde.journalApp.repository;
import com.vaibhavsinghsde.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}