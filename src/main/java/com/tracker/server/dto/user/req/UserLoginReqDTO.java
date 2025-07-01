package com.tracker.server.dto.user.req;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tracker.server.utils.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginReqDTO {
    @NotBlank
    private String userId;
    private String userNm;
    private Gender gender = Gender.O;
    private String age_grp;
    private String profileImage;
}