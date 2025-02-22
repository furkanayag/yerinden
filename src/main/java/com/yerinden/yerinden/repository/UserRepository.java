package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailAndIsActive(String email, Boolean isActive);
    Optional<User> findByIdAndIsActive(Long id, Boolean isActive);
}
