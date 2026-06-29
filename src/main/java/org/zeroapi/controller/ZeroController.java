package org.zeroapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zeroapi.dto.PostResponse;
import org.zeroapi.dto.UserResponse;
import org.zeroapi.service.PostService;
import org.zeroapi.service.UserService;

@Slf4j
@RestController
public class ZeroController {

    @Autowired
    UserService service;

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String home(){
        String gojo = "I alone am the honoured one";
        return gojo;
    }

    @GetMapping("/user/{id}")
    public UserResponse getStudents(@PathVariable int id){
        try {
            logUser(SecurityContextHolder.getContext().getAuthentication());
            return service.getUser(id);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return null;
        }

    }

    @GetMapping("/user/{id}/{field}")
    public String getUserParameter(@PathVariable int id, @PathVariable String field){
        try {
            logUser(SecurityContextHolder.getContext().getAuthentication());
            return service.getField(id, field);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    @GetMapping("/posts/{id}")
    public PostResponse getPost(@PathVariable int id){
        logUser(SecurityContextHolder.getContext().getAuthentication());
        return postService.getPost(id);
    }

    private void logUser(Authentication authentication){
        log.info("The user currently accessing this is {}\nThe role is {}",authentication.getName(),authentication.getAuthorities());
    }
}
