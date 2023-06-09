package com.lenibonje.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_detail")
public class User {

    protected User() {
    }


    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "name should have at least 2 characters")
    @JsonProperty("user_name")
    private String username;

    @Past(message = "birth date should be in the past")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy ="user")
    @JsonIgnore
    private List<Post> posts;

    public User(Integer userId, String username, LocalDate birthDate) {
        this.id = userId;
        this.username = username;
        this.birthDate = birthDate;
    }


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", username='" + username + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Component
    public static class UserDaoService {

        private static final List<User> users = new ArrayList<>();

        static {
            users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
            users.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
            users.add(new User(3, "Abel", LocalDate.now().minusYears(20)));
        }

        public static List<User> findAll() {
            return users;
        }

    }
}
