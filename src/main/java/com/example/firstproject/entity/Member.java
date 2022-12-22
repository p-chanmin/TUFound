package com.example.firstproject.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "member")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Builder
    public Member(Long id, String email, String username, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    protected Member() {}
}