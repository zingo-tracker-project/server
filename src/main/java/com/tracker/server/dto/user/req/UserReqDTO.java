package com.tracker.server.dto.user.req;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserReqDTO {
    @NotBlank(message = "id는 필수값입니다.")
    private String userId;
    @NotBlank(message = "이름은 필수값입니다.")
    private String name;
    private String email;
}