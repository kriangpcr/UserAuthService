package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User update(User user){
        return repository.save(user);
    }
    public User updateName(String id,String name) throws UserException {
        Optional<User> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw UserException.userNotFound();
        }
        User user = optional.get();
        user.setName(name);
        return repository.save(user);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public boolean checkPassword(String rawPassword,String encodePassword){
        return passwordEncoder.matches(rawPassword,encodePassword);
    }

    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }
    public Optional<User> findById(String id){
        return repository.findById(id);
    }

    public User create(String email, String password, String name) throws UserException {
        // validate inputs
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }
        if (Objects.isNull(password)) {
            throw UserException.createPasswordNull();
        }
        if (Objects.isNull(name)) {
            throw UserException.createNameNull();
        }

        // verify if email exists
        if (repository.existsByEmail(email)) {
            throw UserException.createEmailDupe();
        }
//        if (repository.existsByName(name)) {
//            throw UserException.createNameDupe();
//        }


        User entity = new User();
        entity.setName(name);
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password)); // encode password
        return repository.save(entity);
    }
}
