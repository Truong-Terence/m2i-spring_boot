package com.example.demo.repository;

import com.example.demo.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u") // Requête en JPQL
    List<User> queryAllUsers();

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    User queryUserByEmail(@Param("email")String email);

    @Query(value = "SELECT u FROM User u WHERE u.id = :id")
    User queryUserById(@Param("id")Long id);
}
