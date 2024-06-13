package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(EndPoints.REGISTER)
    public ResponseEntity<String> register(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "surname") String surname,
                                           @RequestParam(name = "email") String email,
                                           @RequestParam(name = "phone") String phone,
                                           @RequestParam(name = "password") String password,
                                           @RequestParam(name = "repassword") String repassword
                                           ) {
        userService.register(name, surname, email,
                phone, password, repassword);


        return ResponseEntity.ok("user created");

    }
    @PostMapping(EndPoints.ACTIVATEACCOUNT)
    public ResponseEntity<String> activateAccount(@RequestParam(name = "activationCode") String activationCode) {
        return ResponseEntity.ok(userService.activateAccount(activationCode));
    }

    @PostMapping(EndPoints.LOGIN)
    public ResponseEntity<String> login(@RequestParam(name = "email") String email,
                                        @RequestParam(name = "password") String password) {
        return ResponseEntity.ok(userService.login(email, password));
    }
}
