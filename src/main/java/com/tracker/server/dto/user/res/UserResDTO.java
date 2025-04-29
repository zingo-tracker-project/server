package com.tracker.server.dto.user.res;

import com.tracker.server.utils.JwtResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResDTO {
    private String userId;
    private String name;
    private String email;
    private JwtResDTO jwt;
}
