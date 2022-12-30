package com.cpdiscuss.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
@Entity(name = "Article")
public class Article {
    @Id
    private String articleId;
    private String name;
    private String content;
    private String creatorName;
    private Date createdAt;
    private Date updatedAt;
    private void init(){
        this.createdAt=new Date();
        this.updatedAt=new Date();
    }
    public Article(String name,String content) {
        this.name=name;
        this.content=content;
        this.init();
    }
    public Article(){
        this.init();
    }
    
    public String getName(){
        return this.name;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content=content;
        
    }
    public void setName(String name){
        this.name=name;
    }
    public String getCreatorName(){
        return this.creatorName;
    }
    public Date getCreatedAt(){
        return this.createdAt;
    }
    public Date getUpdatedAt(){
        return this.updatedAt;
    }
}
