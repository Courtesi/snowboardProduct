package com.example.nobsv2.security;

import com.example.nobsv2.security.models.CustomUser;
import com.example.nobsv2.security.services.CreateNewUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateNewUserController {

    private final CreateNewUserService createNewUserService;

    public CreateNewUserController(CreateNewUserService createNewUserService) {
        this.createNewUserService = createNewUserService;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {
        return createNewUserService.execute(user);
    }
}
