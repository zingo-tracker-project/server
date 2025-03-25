package com.tracker.server.controller.user;

import com.tracker.server.dto.user.req.UserReqDTO;
import com.tracker.server.dto.user.res.UserResDTO;
import com.tracker.server.entity.user.User;
import com.tracker.server.service.user.UserService;
import com.tracker.server.utils.ApiResponseEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO Filter를 이용한 로깅 및 보안처리 필요

    @PostMapping("")
    public ApiResponseEntity<UserResDTO> createUser(@RequestBody @Valid UserReqDTO userReqDto) {
        UserResDTO savedUser = userService.createUser(userReqDto);
        return ApiResponseEntity.ok("",savedUser);
    }
}
