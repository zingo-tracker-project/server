package com.tracker.server.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String email;
}