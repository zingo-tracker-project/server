package com.tracker.server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Builder
    public User(String id, String email, String name){
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
