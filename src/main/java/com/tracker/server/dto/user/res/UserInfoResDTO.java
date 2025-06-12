package com.tracker.server.dto.user.res;

import com.tracker.server.entity.user.User;
import com.tracker.server.utils.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResDTO {

    private String userId;

    private Gender gender;

    private String userNm;

    private boolean isActive;

    private String ageGrp;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private String profileImage;

    public static UserInfoResDTO fromEntity(User user) {
        return UserInfoResDTO.builder()
                .userId(user.getUserId())
                .gender(user.getGender())
                .userNm(user.getUserNm())
                .isActive(user.isActive())
                .ageGrp(user.getAgeGrp())
                .createdAt(user.getCreatedAt())
                .deletedAt(user.getDeletedAt())
                .profileImage(user.getProfileImage())
                .build();
    }
}
