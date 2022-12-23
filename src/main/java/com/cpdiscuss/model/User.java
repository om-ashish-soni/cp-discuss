package com.cpdiscuss.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
  
@Document(collection = "users")
@Entity(name = "User")
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String password;
      
    public User(String email, String password) {
        super();
        this.email=email;
        this.password=password;
    }
    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
}