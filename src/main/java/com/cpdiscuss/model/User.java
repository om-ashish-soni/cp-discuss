package com.cpdiscuss.model;


import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection = "users")
@Entity(name = "User")
public class User {
    @Id
    private String userId;
    private String userName;
    private String email;
    private String password;
    private Date birthDate;
    private Date joinDate;


     
    public User(String userName,String email, String password,Date birthDate,Date joinDate) {
        super();
        this.userName = userName;
        this.email = email;
        this.birthDate=birthDate;
        this.password=password;
        this.joinDate=joinDate;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String name) {
        this.userName = name;
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
    public void setPassword(String password) {
        this.password=password;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Date getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}


// package com.cpdiscuss.model;

// import java.util.Date;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
  
// @Document(collection = "users")
// @Entity(name = "User")
// public class User {
//     @Id
//     private String userId;
//     private String userName;
//     private String email;
//     private String password;
//     private Date birthDate;

      
//     public User(String userName,String email, String password,Date birthDate) {
//         super();
//         this.userName = userName;
//         this.email = email;
//         this.birthDate=birthDate;
//         this.password=password;
//     }
//     public String getUserId() {
//         return userId;
//     }
//     public void setUserId(String userId) {
//         this.userId = userId;
//     }
//     public String getUserName() {
//         return userName;
//     }
//     public void setUserName(String name) {
//         this.userName = name;
//     }
//     public String getEmail() {
//         return email;
//     }
//     public void setEmail(String email) {
//         this.email = email;
//     }
//     public String getPassword() {
//         return password;
//     }
//     public void setPassword(String password) {
//         this.password=password;
//     }
//     public Date getBirthDate() {
//         return birthDate;
//     }
//     public void setBirthDate(Date birthDate) {
//         this.birthDate = birthDate;
//     }
// }