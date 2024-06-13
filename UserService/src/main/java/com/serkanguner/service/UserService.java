package com.serkanguner.service;


import com.serkanguner.dto.request.MailCheckUpdateStatus;
import com.serkanguner.entity.Role;
import com.serkanguner.entity.Status;
import com.serkanguner.entity.User;
import com.serkanguner.exception.UserServiceException;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.repository.UserRepository;
import com.serkanguner.utility.CodeGenerator;
import com.serkanguner.utility.JwtTokenManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final RabbitTemplate rabbitTemplate;



    public String register(String name, String surname, @Valid String email, String phone, String password, String repassword) {


        if (userRepository.existsByEmail(email)){
            throw new UserServiceException(ErrorType.USER_ALREADY_EXISTS);
        }
        if (!password.equals(repassword)) {
            throw new UserServiceException(ErrorType.USER_PASSWORD_NOT_MATCH);
        }
        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phone(phone)
                .password(password)
                .build();
        user.setActivationCode(CodeGenerator.generateActivationCode());
        userRepository.save(user);

        MailCheckUpdateStatus mailCheckUpdateStatus = MailCheckUpdateStatus.builder()
                .email(email)
                .activationCode(user.getActivationCode())
                .build();
        rabbitTemplate.convertAndSend("exchange.direct", "Routing.mail", mailCheckUpdateStatus);

        return "User Created";
    }
    public String activateAccount(String activationCode) {
        User user = userRepository.findByActivationCode(activationCode);
        if (user == null) {
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "Account activated";
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user.getStatus().equals(Status.INACTIVE)) {
            throw new UserServiceException(ErrorType.USER_NOT_ACTIVE);
        }
        if (user != null && user.getPassword().equals(password)) {
            return jwtTokenManager.createToken(user.getId(), user.getName(), user.getRole()).get();
        } else {
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
    }


}
