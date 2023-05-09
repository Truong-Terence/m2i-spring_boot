package com.example.demo.Service;

import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.queryAllContacts();
    }
    public Contact getById(Long id) {
        return contactRepository.getById(id);
    }
    public List<Contact> getUserById(Long id) {
        return contactRepository.findByUserId(id);
    }
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
    public void deleteContact(Long contactId) {
        contactRepository.deleteContactById(contactId);
    }

}
