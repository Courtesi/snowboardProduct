package com.example.nobsv2.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CreateNewUserController {
    private final PasswordEncoder encoder;

    private final CustomUserRepository customUserRepository;

    public CreateNewUserController(PasswordEncoder encoder, CustomUserRepository customUserRepository) {
       this.encoder = encoder;
       this.customUserRepository = customUserRepository;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {

        //this should go in a service class (trying to keep it simple rn)
        //need better error handling
        Optional<CustomUser> optionalUser = customUserRepository.findById(user.getUsername());

        if (optionalUser.isEmpty()) {
            CustomUser newUser = new CustomUser();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(encoder.encode(user.getPassword()));
            customUserRepository.save(newUser);

            return ResponseEntity.ok("Success");
        }

        return ResponseEntity.badRequest().body("Failure");
    }
}
