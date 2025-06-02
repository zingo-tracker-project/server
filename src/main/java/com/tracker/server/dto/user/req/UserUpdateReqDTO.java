package com.tracker.server.dto.user.req;

import com.tracker.server.utils.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateReqDTO {

    private String userId;
    private Gender gender;

    private String userNm;

    private String ageGrp;

    private String profileImage;
}
