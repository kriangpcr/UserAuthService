package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "k_social")
public class Social extends BaseEntity {

    @Column(length = 120)
    private String facebook;

    @Column(length = 120)
    private String line;

    @Column(length = 120)
    private String instagram;

    @Column(length = 120)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "k_user_id",nullable = false)
    private User user;
}
