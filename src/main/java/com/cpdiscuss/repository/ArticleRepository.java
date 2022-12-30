package com.cpdiscuss.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpdiscuss.model.Article;
@Repository
public interface ArticleRepository extends MongoRepository<Article,String>{
    @Query("{'name':?0}")
    Article findByName(String name);
}
