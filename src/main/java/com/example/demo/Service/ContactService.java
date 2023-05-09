package com.example.demo.Service;

import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public Contact getById(Long id) {
        return contactRepository.queryContactById(id);
    }
}
