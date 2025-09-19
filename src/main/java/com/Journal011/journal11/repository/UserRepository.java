package com.Journal011.journal11.repository;
import com.Journal011.journal11.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {


    User findByUserName(String username);

    void deleteByUserName(String userName);


}