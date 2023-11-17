package dev.padak.backend.service;

import dev.padak.backend.entity.UserEntity;
import dev.padak.backend.repository.UserRepository;
import dev.padak.backend.config.UserDetailsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userInfo = userRepository.findByUsername(username);
        return userInfo.map(UserDetailsConfig::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
