package com.dmytro.komyshnyi.ec2.repository;

import com.dmytro.komyshnyi.ec2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    void deleteById(UUID id);
}
