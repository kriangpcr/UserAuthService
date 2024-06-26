package com.example.demo.api;

import com.example.demo.business.UserBusiness;
import com.example.demo.exception.BaseException;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserBusiness business;


    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) throws BaseException {
        RegisterResponse register = business.register(request);
        return ResponseEntity.ok(register);
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws BaseException {
        String token = business.login(request);
        return ResponseEntity.ok(token);
    }

    @GetMapping
    @RequestMapping("/refresh-token")
    public ResponseEntity<String> refreshToken( ) throws BaseException {
        String token = business.refreshToken();
        return ResponseEntity.ok(token);
    }


}
