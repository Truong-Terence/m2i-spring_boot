package com.example.demo.repository;

import com.example.demo.repository.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "SELECT c FROM Contact c WHERE c.id = :id")
    Contact queryContactById(@Param("id")Long id);
}
