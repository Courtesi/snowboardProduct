package com.example.nobsv2.security.services;

import com.example.nobsv2.Command;
import com.example.nobsv2.security.CustomUserRepository;
import com.example.nobsv2.security.models.CustomUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateNewUserService implements Command<CustomUser, String> {

    private final CustomUserRepository customUserRepository;
    private final PasswordEncoder encoder;

    public CreateNewUserService(CustomUserRepository customUserRepository, PasswordEncoder encoder) {
        this.customUserRepository = customUserRepository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<String> execute(CustomUser user) {
        Optional<CustomUser> optionalUser = customUserRepository.findById(user.getUsername());

        if (optionalUser.isEmpty()) {
            CustomUser newUser = new CustomUser();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(encoder.encode(user.getPassword()));
            newUser.setRole("normal");
            customUserRepository.save(newUser);

            return ResponseEntity.ok("Success");
        }

        //need better error handling
        return ResponseEntity.badRequest().body("Failure");
    }
}