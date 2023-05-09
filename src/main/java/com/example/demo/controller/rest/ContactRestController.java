package com.example.demo.controller.rest;

import com.example.demo.Service.ContactService;
import com.example.demo.Service.UserService;
import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users/{user_id}/contacts")
public class ContactRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts(@PathVariable Long user_id) {
        User user = userService.getById(user_id);
        return user.getContacts();
    }

    @PostMapping
    public Contact addContact(@PathVariable Long user_id, @RequestBody Contact contact) {
        User user = userService.getById(user_id);
        contact.setUser(user);
        Contact savedContact = contactService.saveContact(contact);
        user.addContact(savedContact);
        userService.saveUser(user);
        return savedContact;
    }

    @GetMapping("/{contact_id}")
    public Contact getContactById(@PathVariable Long user_id, @PathVariable Long contact_id) {
        Contact contact = (Contact) contactService.getById(contact_id);
        if (contact.getUser().getId().equals(user_id)) {
            return contact;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{contact_id}")
    public Contact updateContact(@PathVariable Long user_id, @PathVariable Long contact_id, @RequestBody Contact updatedContact) {
        Contact contact = contactService.getById(contact_id);
        if (contact.getUser().getId().equals(user_id)) {
            updatedContact.setId(contact_id);
            updatedContact.setUser(contact.getUser());
            return contactService.saveContact(updatedContact);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping("/{contact_id}")
    public void deleteContact(@PathVariable Long user_id, @PathVariable Long contact_id) {
        Contact contact = contactService.getById(contact_id);
        if (contact.getUser().getId().equals(user_id)) {
            User user = contact.getUser();
            user.removeContact(contact);
            userService.saveUser(user);
            contactService.deleteContact(contact_id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}


