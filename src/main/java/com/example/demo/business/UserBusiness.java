package com.example.demo.business;

import com.example.demo.entity.User;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.UserException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;


    public String refreshToken() throws UserException {

        Optional<String> optional = SecurityUtil.getCurrentUserId();
        if (optional.isEmpty()) {
            throw UserException.userNotFound();
        }
        String userId = optional.get();
        Optional<User> optionalUser = userService.findById(userId);

        if (optionalUser.isEmpty()) {
            throw UserException.userNotFound();
        }

        User user = optionalUser.get();
        return tokenService.tokenize(user);
    }

    public RegisterResponse register(RegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
        RegisterResponse registerResponse = userMapper.toRegisterResponse(user);
        return registerResponse;
    }

    public String login(LoginRequest request) throws BaseException {

        Optional<User> byEmail = userService.findByEmail(request.getEmail());

        if (byEmail.isEmpty()) {
            throw UserException.loginEmailNotFound();
        }
        User user = byEmail.get();

        if (!userService.checkPassword(request.getPassword(), user.getPassword())) {
            throw UserException.loginPasswordIncorrect();
        }

        return tokenService.tokenize(user);
    }
}
