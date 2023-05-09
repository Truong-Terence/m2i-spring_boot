package com.example.demo.repository;

import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {


    @Query(value = "SELECT c FROM Contact c") // Requête en JPQL
    List<Contact> queryAllContacts();

    @Query(value = "SELECT c FROM Contact c WHERE c.id = :id")
    Contact getById(Long id);


    List<Contact> findByUserId(Long userId);
    void deleteContactById(Long contactId);
}
