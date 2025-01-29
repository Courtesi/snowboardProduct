package com.example.nobsv2.security.services;

import com.example.nobsv2.security.CustomUserRepository;
import com.example.nobsv2.security.models.CustomUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //can add logic to deal with the optional
        Optional<CustomUser> customUserOptional = customUserRepository.findById(username);

        if (customUserOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        CustomUser customUser = customUserOptional.get();

        UserDetails user;
        if (customUser.getRole().equals("admin")) {
            user = User
                    .withUsername(customUser.getUsername())
                    .password(customUser.getPassword())
                    .authorities("admin")
                    .build();
        } else {
            user = User
                    .withUsername(customUser.getUsername())
                    .password(customUser.getPassword())
                    .build();
        }

        return user;
    }
}
