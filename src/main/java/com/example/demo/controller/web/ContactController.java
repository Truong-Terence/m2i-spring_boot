package com.example.demo.controller.web;

import com.example.demo.Service.ContactService;
import com.example.demo.Service.UserService;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users/{user_id}/contacts", method = RequestMethod.GET)
    public String displayContacts(Model model, @PathVariable Long user_id) {
        User user = userService.getById(user_id);
        List<Contact> contacts = user.getContacts();
        model.addAttribute("contacts", contacts);
        model.addAttribute("user", user);
        return "contacts";
    }

    @RequestMapping(value = "/users/{user_id}/contacts", method = RequestMethod.POST)
    public String addContact(@PathVariable Long user_id, @ModelAttribute Contact contact, Model model) {
        User user = userService.getById(user_id);
        user.addContact(contact);
        userRepository.save(user);
        List<Contact> contacts = user.getContacts();
        model.addAttribute("contacts", contacts);
        model.addAttribute("user", user);
        return "contacts";
    }



    @DeleteMapping("/users/{user_id}/contacts/{contact_id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long user_id, @PathVariable Long contact_id) {
        Contact contact = contactService.getById(contact_id);
        if (!contact.getUser().getId().equals(user_id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot delete contact of another user");
        }

        contactService.deleteContact(contact_id);
        return ResponseEntity.noContent().build();
    }
}
