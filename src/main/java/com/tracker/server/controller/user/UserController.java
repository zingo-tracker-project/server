package com.tracker.server.controller.user;

import com.tracker.server.dto.user.req.UserReqDTO;
import com.tracker.server.dto.user.res.UserResDTO;
import com.tracker.server.entity.user.User;
import com.tracker.server.service.user.UserService;
import com.tracker.server.utils.ApiResponseEntity;
import com.tracker.server.utils.CommonConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user") // User API
@Tag(name = "User API", description = "사용자 API")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    @Operation(summary = "회원가입", description = "회원가입 API")
    public ApiResponseEntity<UserResDTO> createUser(@RequestBody @Valid UserReqDTO userReqDto) {
        UserResDTO savedUser = userService.createUser(userReqDto);
        return ApiResponseEntity.ok(CommonConstants.GLOBAL_SUCCESS_MSG,savedUser);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getMyInfo(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok("id :  " + userId);
    }

    // TODO 회원 정보 수정

    // TODO 회원 정보 삭제

    // TODO 회원 정보 가져오기
}
