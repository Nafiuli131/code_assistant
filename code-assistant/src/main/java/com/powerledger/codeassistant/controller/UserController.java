package com.powerledger.codeassistant.controller;

import com.powerledger.codeassistant.dto.UserRequest;
import com.powerledger.codeassistant.model.User;
import com.powerledger.codeassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<String> saveUser(@RequestBody UserRequest request) {
        try {
            boolean result = userService.saveUser(request);
            if (result) {
                return new ResponseEntity<>("User saved successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User already exist", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<User> getUser(@RequestParam String userName) {
        try {
            User user = userService.getUser(userName);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
