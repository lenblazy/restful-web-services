package com.lenibonje.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")

    public List<User> getUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")

    public User retrieveUser(@PathVariable int id) {
        return userDaoService.findOne(id);
    }


}
