package org.zeroapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zeroapi.dto.UserResponse;
import org.zeroapi.service.UserService;

@Slf4j
@RestController
public class OneController {

    @Autowired
    private UserService service;

    @GetMapping("/adminuser")
    public UserResponse getuserforadmin(@RequestParam int id){
        try {
            return service.getUser(id);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return null;
        }

    }
}
