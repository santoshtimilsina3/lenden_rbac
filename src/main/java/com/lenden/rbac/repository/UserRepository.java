package com.lenden.rbac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lenden.rbac.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
