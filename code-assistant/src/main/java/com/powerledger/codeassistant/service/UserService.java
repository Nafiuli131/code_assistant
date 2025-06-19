package com.powerledger.codeassistant.service;


import com.powerledger.codeassistant.dto.UserRequest;
import com.powerledger.codeassistant.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    boolean saveUser(UserRequest request);

    User getUser(String userName);
}
