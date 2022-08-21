package com.dmytro.komyshnyi.ec2.service;

import com.dmytro.komyshnyi.ec2.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getAll();
    User findByUsername(String username);
    User findById(UUID id);
    void delete(UUID id);
    User register(User user);
}
