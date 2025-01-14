package com.tracker.server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@ToString(exclude = "pwd")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String pwd;

    @Column(name = "name")
    private String name;

    @Builder
    public User(int id, String email, String pwd, String name){
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
    }
}
