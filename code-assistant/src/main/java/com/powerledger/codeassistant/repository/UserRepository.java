package com.powerledger.codeassistant.repository;

import com.powerledger.codeassistant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userName=:userName ")
    User findByUserName(String userName);
}
