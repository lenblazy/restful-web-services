package com.lenibonje.rest.webservices.restfulwebservices.user;

import com.lenibonje.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.lenibonje.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private final UserRepository repository;
    private final PostRepository postRepository;

    public UserJpaResource(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id: " + id + " not found");

        EntityModel<User> userModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());
        userModel.add(link.withRel("all-users"));

        return userModel;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id: " + id + " not found");

        return user.get().getPosts();
    }

    @GetMapping(path = "/jpa/users/{id}/posts/{post_id}")
    public Post retrieveSinglePost(@PathVariable int id, @PathVariable int post_id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id: " + id + " not found");

        Optional<Post> post = user.get().getPosts().stream()
                .filter(it -> it.getId() == post_id).findFirst();

        if (post.isEmpty()) throw new PostNotFoundException("id: " + id + " not found");

        return post.get();
    }


    //POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id: " + id + " not found");

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }



}
