package com.cpdiscuss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpdiscuss.model.Article;
import com.cpdiscuss.model.Comment;
import com.cpdiscuss.repository.ArticleRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

@RestController()
@CrossOrigin(origins = "*")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public Map<String, Object> articles() {
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articleRepository.findAll());
        return map;
    }

    @PostMapping("/articles/create")
    public Map<String, Object> createArticle(@RequestBody Article article) {
        String articleName = article.getName();
        articleName = articleName.replace(' ', '-');
        article.setName(articleName);
        article.setViews(article.getViews() + 1);
        Article existingArticle = getArticle(articleName);

        Map<String, Object> response = new HashMap<>();
        if (existingArticle != null) {
            response.put("ok", (Object) false);
            response.put("error", "Article with given name" + articleName + " already exists");
            return response;
        }
        articleRepository.save(article);
        response.put("ok", (Object) true);
        response.put("article", article);
        return response;
    }

    @GetMapping("/articles/get/{articleName}")
    public Article getArticle(@PathVariable String articleName) {
        Article article = articleRepository.findByName(articleName);
        if (article == null)
            return article;
        article.setViews(article.getViews() + 1);
        articleRepository.save(article);
        return article;
    }

    @GetMapping("/articles/search/{articleName}")
    public Map<String, Object> getArticleSearch(@PathVariable String articleName) {
        String searchText=articleName;
        Map<String, Object> response = new HashMap<String, Object>();
        String[] searchTextWords = searchText.split(" ");
        StringBuilder regexBuilder = new StringBuilder();
        for (String word : searchTextWords) {
            regexBuilder.append(".*").append(word);
        }
        regexBuilder.append(".*");
        String regex = regexBuilder.toString();
        response.put("regex",regex);
        List<Article> searchResults=articleRepository.findArticlesByRegex(regex);
        response.put("searchResults",searchResults);
        return response;
    }

    @PutMapping("/articles/like/{articleName}")
    public Map<String, Object> likeArticle(@PathVariable String articleName) {
        Article article = articleRepository.findByName(articleName);
        article.setLikes(article.getLikes() + 1);
        articleRepository.save(article);
        Map<String, Object> response = new HashMap<>();
        response.put("ok", (Object) true);
        response.put("likes", (Object) article.getLikes());
        return response;
    }

    @PutMapping("/articles/vote-difficulty/{articleName}/{difficultyType}")
    public Map<String, Object> voteDifficultyOfArticle(@PathVariable String articleName,
            @PathVariable String difficultyType) {
        Article article = articleRepository.findByName(articleName);
        article.voteDifficultyType(difficultyType);
        articleRepository.save(article);
        Map<String, Object> response = new HashMap<>();
        response.put("ok", (Object) true);
        response.put("difficultyType", (Object) article.getDifficultyType());
        return response;
    }

    @GetMapping("/articles/get/tag/{tagName}")
    public Map<String, Object> getArticlesByTag(@PathVariable String tagName) {
        List<Article> articles = articleRepository.findByTag(tagName);

        Map<String, Object> response = new HashMap<>();
        response.put("articles", (Object) articles);
        return response;
    }

    @GetMapping("/articles/get/creator/{creatorName}")
    public Map<String, Object> getArticlesByCreatorName(@PathVariable String creatorName) {
        List<Article> articles = articleRepository.findByCreatorName(creatorName);

        Map<String, Object> response = new HashMap<>();
        response.put("articles", (Object) articles);
        return response;
    }

    // @PostMapping("/articles/comment/{articleName}")
    public Article commentArticle(String articleName,Comment comment) {
        Article article=articleRepository.findByName(articleName);
        article.addComment(comment);
        articleRepository.save(article);
        return article;
    }
    // @GetMapping("/articles/delete/{articleName}")
    // public Map<String,Object> deleteArticle(@PathVariable String articleName) {
    // Map<String,Object> response=new HashMap<>();
    // Article article=getArticle(articleName);
    // if(article==null) {
    // }
    // }

}
