package com.example.demo.service;

import com.example.demo.entity.Social;
import com.example.demo.entity.User;
import com.example.demo.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialService {

    @Autowired
    private SocialRepository repository;

    Optional<Social> findByUser(User user){
        return repository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok) {

        Social entity = new Social();

        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);

        return repository.save(entity);
    }
}
