package org.zeroapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zeroapi.dto.UserResponse;
import org.zeroapi.service.JsonPlaceholderService;

@Slf4j
@RestController
public class ZeroController {

    @Autowired
    JsonPlaceholderService service;

    @GetMapping("/")
    public String home(){
        String gojo = "I alone am the honoured one";
        return gojo;
    }

    @GetMapping("/user/{id}")
    public UserResponse getStudents(@PathVariable int id){
        try {
            return service.getUser(id);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return null;
        }

    }

    @GetMapping("/user/{id}/{field}")
    public String getUserParameter(@PathVariable int id, @PathVariable String field){
        try {
            return service.getField(id, field);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
