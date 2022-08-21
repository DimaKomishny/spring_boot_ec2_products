package com.dmytro.komyshnyi.ec2.security;

import com.dmytro.komyshnyi.ec2.entity.User;
import com.dmytro.komyshnyi.ec2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailServiceImpl")
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("User does not exist"));
        return SecurityUser.fromUser(user);
    }
}
