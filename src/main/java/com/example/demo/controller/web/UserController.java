package com.example.demo.controller.web;

import com.example.demo.Service.ContactService;
import com.example.demo.Service.UserService;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

//    FETCH ALL
    @RequestMapping(method = RequestMethod.GET)
    public String displayAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "home";
    }

//    REGISTRATION
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        saveUser(user);
        return "registration-success";
    }

//    LOGIN
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepository.queryUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);
            return "contacts";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "home";
    }

//    @RequestMapping(value = "/users/{id}/add-contact", method = RequestMethod.POST)
//    public String addContact(@PathVariable("id") Long id, @RequestParam("contact_id") Long contact_id) {
//        User user = userService.getById(id);
//        Contact contact = contactService.getById(contact_id);
//
//        user.addContact(contact);
//        userRepository.save(user);
//
//        return "redirect:/users/" + id;
//    }
//
//    @PostMapping("/users/{userId}/contacts/remove")
//    public String removeContact(@PathVariable Long user_id, @RequestParam Long contact_id, RedirectAttributes redirectAttributes) {
//        User user = userService.getById(user_dd);
//        Contact contact = contactService.getById(contact_dd);
//
//        if(user != null && contact != null && user.getContacts().contains(contact)) {
//            user.removeContact(contact);
//            userRepository.save(user);
//            redirectAttributes.addFlashAttribute("successMessage", "Contact removed successfully");
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", "Could not remove contact");
//        }
//
//        return "redirect:/users/" + user_id;
//    }



}
