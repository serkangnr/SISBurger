package com.serkanguner.service;


import com.serkanguner.entity.Admin;
import com.serkanguner.exception.AdminServiceException;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.repository.AdminRepository;
import com.serkanguner.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final JwtTokenManager jwtTokenManager;
    @Value("${adminservice.secret.admin-name}")
    private String adminName;
    @Value("${adminservice.secret.admin-password}")
    private String adminPassword;

    public String register(String name,
                           String password,
                           String adminname,
                           String adminpassword) {

        if (adminname.equals(adminName) && adminpassword.equals(adminPassword)) {
            Admin admin = Admin.builder()
                    .name(name)
                    .password(password)
                    .build();
            adminRepository.save(admin);
            return "Admin created successfully";


        } else {
            throw new AdminServiceException(ErrorType.ADMIN_NOT_CREATED);
        }


    }

    public String login(String name, String password) {
        Admin admin = adminRepository.findByName(name);

        if (admin!= null && adminName.equals(name) && adminPassword.equals(password)) {
            return jwtTokenManager.createToken(admin.getId(),admin.getName(),admin.getPassword(),admin.getRole()).get();
        } else {
            throw new AdminServiceException(ErrorType.ADMIN_NOT_FOUND);
        }
    }
}
