package com.dmytro.komyshnyi.ec2.service.impl;

import com.dmytro.komyshnyi.ec2.entity.User;
import com.dmytro.komyshnyi.ec2.exception.UserNotFoundException;
import com.dmytro.komyshnyi.ec2.repository.UserRepository;
import com.dmytro.komyshnyi.ec2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User %s is not exist ", username)));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id %s is not exist ", id)));
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
}
