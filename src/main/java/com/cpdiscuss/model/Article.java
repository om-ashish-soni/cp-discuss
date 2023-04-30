package com.cpdiscuss.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
@Entity(name = "Article")
public class Article {

    @Id
    private String articleId;
    private String name;
    private String content;
    private String summary;
    private String creatorName;
    private String creatorId;
    private Date createdAt;
    private Date updatedAt;
    private Set<String> tags;
    private Long views;
    private Long likes;
    public String difficultyType;
    private static final String[] difficultyList={
        "easy","medium","hard","normal","expert"
    };
    private Map<String,Integer> difficultyTypeMap;
    private List<Comment> comments;
    private void init(){
        this.createdAt=new Date();
        this.updatedAt=new Date();
        this.views=0L;
        this.likes=0L;
        this.comments=new ArrayList<Comment>();
        this.difficultyType="easy";
        this.difficultyTypeMap=new HashMap<String,Integer>();
        this.tags=new HashSet<>();
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
    public List<Comment> getComments(){
        return this.comments;
    }
    public void addComment(Comment comment){
        this.comments.add(comment);
    }
    public String getSummary(){
        return this.summary;
    }
    public void setSummary(String summary){
        this.summary=summary;
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
    public String getCreatorId(){
        return this.creatorId;
    }
    public void setCreatorId(String id){
        this.creatorId=id;
        
    }
    public void setCreatorName(String creatorName){
        this.creatorName=creatorName;
    }
    public String getName(){
        return this.name;
    }
    public String getContent(){
        return this.content;
    }
    public Set<String> getTags(){
        return this.tags;
    }
    public void setTags(List<String> tags){
        this.tags.clear();
        addTags(tags);
    }
    public void addTag(String tag){
        this.tags.add(tag);
    }
    public void addTags(List<String> tags){
        for(String tag:tags){
            this.tags.add(tag);
        }
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
