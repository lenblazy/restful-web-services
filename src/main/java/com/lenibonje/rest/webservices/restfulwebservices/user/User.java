package com.lenibonje.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_detail")
public class User {

    @Id
    @GeneratedValue
    private Integer userId;

    @Size(min=2, message = "name should have at least 2 characters")
    @JsonProperty("user_name")
    private String username;

    @Past(message = "birth date should be in the past")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    public User(Integer userId, String username, LocalDate birthDate) {
        this.userId = userId;
        this.username = username;
        this.birthDate = birthDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
                "userId=" + userId +
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
