package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "k_address")
public class Address extends BaseEntity {

    @Column(length = 120)
    private String line1;

    @Column(length = 120)
    private String line2;

    @Column(length = 120)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "k_user_id",nullable = false)
    private User user;
}
