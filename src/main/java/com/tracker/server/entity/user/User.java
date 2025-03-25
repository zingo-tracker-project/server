package com.tracker.server.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "TB_USER")
@Data
@NoArgsConstructor // jpa가 내부적으로 사용하기위한 생성자
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

}
