package com.tracker.server.entity.user;

import com.tracker.server.utils.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "gender", columnDefinition = "CHAR(1)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "user_nm", nullable = false)
    private String userNm;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive;

    @Column(name = "age_grp")
    private String ageGrp;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
