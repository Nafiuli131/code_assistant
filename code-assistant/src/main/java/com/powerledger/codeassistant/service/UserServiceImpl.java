package com.powerledger.codeassistant.service;

import com.powerledger.codeassistant.dto.UserRequest;
import com.powerledger.codeassistant.model.User;
import com.powerledger.codeassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean saveUser(UserRequest request) {
        User existingUser = userRepository.findByUserName(request.getUserName());
        if (existingUser == null) {
            User user = new User();
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }
}
