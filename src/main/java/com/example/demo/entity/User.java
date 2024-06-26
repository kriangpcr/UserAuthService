package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "k_user")
public class User extends BaseEntity {

    @Column(nullable = false,unique = true,length = 60)
    private String email;

    @JsonIgnore
    @Column(nullable = false,length = 60)
    private String password;

    @Column(nullable = false,length = 120)
    private String name;

    @OneToOne(mappedBy = "user",orphanRemoval = true)
    private Social social;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Address> addresses;

}
