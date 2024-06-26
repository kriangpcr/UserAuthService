package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Social;
import com.example.demo.entity.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    List<Address> findByUser(User user){
        return repository.findByUser(user);
    }

    public Address create(User user, String line1, String line2, String zipcode) {

        Address entity = new Address();

        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);

        return repository.save(entity);
    }
}
