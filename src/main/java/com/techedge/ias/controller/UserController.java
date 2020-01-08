package com.techedge.ias.controller;

import com.techedge.ias.model.UserDetail;
import com.techedge.ias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/user/list")
    public List<UserDetail> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @PostMapping(value = "/user/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDetail addUser(@RequestBody UserDetail userDetail) {
        return userService.addUser(userDetail);
    }

}

