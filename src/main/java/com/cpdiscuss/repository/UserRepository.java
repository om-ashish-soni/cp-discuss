package com.cpdiscuss.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpdiscuss.model.User;
@Repository
public interface UserRepository extends MongoRepository<User,String>{
    @Query("{ 'userName' : ?0 }")    
    User findByUserName(String userName);
}
