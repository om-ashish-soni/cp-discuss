package com.cpdiscuss.controller;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpdiscuss.model.Article;
import com.cpdiscuss.repository.ArticleRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

@RestController()
public class ArticleController{

    @Autowired
    private ArticleRepository articleRepository;

    

    @GetMapping("/articles")
    public Map<String,Object> articles() {
        Map<String,Object> map = new HashMap<>();
        map.put("articles", articleRepository.findAll());
        return map;
    }


    @PostMapping("/articles/create")
    public Map<String,Object> createArticle(@RequestBody Article article) {
        String articleName=article.getName();
        articleName=articleName.replace(' ','-');
        article.setName(articleName);
        article.setViews(article.getViews()+1);
        Article existingArticle=getArticle(articleName);

        Map<String,Object> response=new HashMap<>();
        if(existingArticle!=null) {
            response.put("ok",(Object)false);
            response.put("error","Article with given name"+articleName+" already exists");
            return response;
        }
        articleRepository.save(article);
        response.put("ok",(Object)true);
        response.put("article",article);
        return response;
    }

    @GetMapping("/articles/get/{articleName}")
    public Article getArticle(@PathVariable String articleName){
        Article article=articleRepository.findByName(articleName);
        if(article==null) return article;
        article.setViews(article.getViews()+1);
        articleRepository.save(article);
        return article;
    }
    
    @PutMapping("/articles/like/{articleName}")
    public Map<String,Object> likeArticle(@PathVariable String articleName){
        Article article=articleRepository.findByName(articleName);
        article.setLikes(article.getLikes()+1);
        articleRepository.save(article);
        Map<String,Object> response=new HashMap<>();
        response.put("ok",(Object)true);
        response.put("likes",(Object)article.getLikes());
        return response;
    }

    @PutMapping("/articles/vote-difficulty/{articleName}/{difficultyType}")
    public Map<String,Object> voteDifficultyOfArticle(@PathVariable String articleName,@PathVariable String difficultyType){
        Article article=articleRepository.findByName(articleName);
        article.voteDifficultyType(difficultyType);
        articleRepository.save(article);
        Map<String,Object> response=new HashMap<>();
        response.put("ok",(Object)true);
        response.put("difficultyType",(Object)article.getDifficultyType());
        return response;
    }
    // @GetMapping("/articles/delete/{articleName}")
    // public Map<String,Object> deleteArticle(@PathVariable String articleName) {
    //     Map<String,Object> response=new HashMap<>();
    //     Article article=getArticle(articleName);
    //     if(article==null) {
    //     }
    // }

    
}
