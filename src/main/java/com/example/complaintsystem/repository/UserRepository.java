package com.example.complaintsystem.repository;
import com.example.complaintsystem.dto.LoginRequest;
import com.example.complaintsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
