package com.example.demo.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.queryUserById(id);
    }

    public void addContact(Long userId, Contact contact) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.addContact(contact);
        userRepository.save(user);
    }
}
