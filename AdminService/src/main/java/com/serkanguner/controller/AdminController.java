package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.ADMIN)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping(EndPoints.REGISTER)
    public ResponseEntity<String> register(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "password") String password,
                                           @RequestParam(name = "adminName") String adminname,
                                           @RequestParam(name = "adminPassword") String adminpassword) {

        adminService.register(name, password, adminname, adminpassword);
        return ResponseEntity.ok("admin created");

    }

    @PostMapping(EndPoints.LOGIN)
    public ResponseEntity<String> login(@RequestParam(name = "name") String name,
                                        @RequestParam(name = "password") String password) {
        return ResponseEntity.ok(adminService.login(name, password));
    }
}
