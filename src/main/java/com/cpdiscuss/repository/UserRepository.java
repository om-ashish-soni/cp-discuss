package com.cpdiscuss.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cpdiscuss.model.User;
@Repository
public interface UserRepository extends MongoRepository<User,String>{
    
}
