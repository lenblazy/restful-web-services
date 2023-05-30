package com.lenibonje.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer userId;
    private String username;
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