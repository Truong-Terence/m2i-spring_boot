package com.example.demo.repository;

import com.example.demo.repository.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User findByFirstname(String firstname);



}
