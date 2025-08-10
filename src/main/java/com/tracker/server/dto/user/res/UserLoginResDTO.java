package com.tracker.server.dto.user.res;

import com.tracker.server.utils.JwtResDTO;
import com.tracker.server.utils.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResDTO {
    private String userId;
    private String userNm;
    private Gender gender;
    private String ageGrp;
    private JwtResDTO jwt;
    private String profileImage;
    boolean isNewUser = false;
}
