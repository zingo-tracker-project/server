package com.tracker.server.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JwtResDTO {
    private String accessToken;
    private String refreshToken;
}
