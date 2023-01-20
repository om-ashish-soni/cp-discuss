package com.cpdiscuss.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<String> tags;
    private Long views;
    private Long likes;
    public String difficultyType;
    private static final String[] difficultyList={
        "easy","medium","hard","normal","expert"
    };
    private Map<String,Integer> difficultyTypeMap;
    
    private void init(){
        this.createdAt=new Date();
        this.updatedAt=new Date();
        this.views=0L;
        this.likes=0L;
        this.difficultyType="easy";
        this.difficultyTypeMap=new HashMap<String,Integer>();
        for(String difficultyType:difficultyList){
            this.difficultyTypeMap.put(difficultyType,0);
        }
        
    }
    public Article(String name,String content) {
        this.name=name;
        this.content=content;
        this.init();
    }
    public Article(String name,String content,String difficultyType) {
        this.name=name;
        this.content=content;
        this.init();
        this.setDifficultyType(difficultyType);
    }
    public Article(){
        this.init();
    }
    public Long getViews(){
        return this.views;
    }
    public void setViews(Long views){
        this.views=views;
    }
    public Long getLikes(){
        return this.likes;
    }
    public void setLikes(Long likes){
        this.likes=likes;
    }
    public String getName(){
        return this.name;
    }
    public String getContent(){
        return this.content;
    }
    public List<String> getTags(){
        return this.tags;
    }
    public void setTags(List<String> tags){
        this.tags=tags;
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
    public String calculateDifficulty(){
        int mx=0;
        String difficulty="easy";
        for(Map.Entry<String,Integer> entry : this.difficultyTypeMap.entrySet()){
            if(entry.getValue()>=mx){
                mx=entry.getValue();
                difficulty=entry.getKey();
            }
        }
        return difficulty;
    }
    public void updateDifficultyType(){
        this.difficultyType=this.calculateDifficulty();
    }
    public void voteDifficultyType(String difficulty){
        int freq=1;
        if(this.difficultyTypeMap.get(difficulty) != null){
            freq+=this.difficultyTypeMap.get(difficulty);
        }
        this.difficultyTypeMap.put(difficulty,freq);
        this.updateDifficultyType();
        return;
    }
    public String getDifficultyType(){
        return this.difficultyType;
    }
    public void setDifficultyType(String difficultyType){
        this.voteDifficultyType(difficultyType);
    }
}
