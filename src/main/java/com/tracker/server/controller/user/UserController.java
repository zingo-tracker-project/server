package com.tracker.server.controller.user;

import com.tracker.server.dto.user.req.UserLoginReqDTO;
import com.tracker.server.dto.user.res.UserLoginResDTO;
import com.tracker.server.service.user.UserService;
import com.tracker.server.utils.ApiResponseEntity;
import com.tracker.server.utils.CommonConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user") // User API
@Tag(name = "User API", description = "사용자 API")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 API")
    public ApiResponseEntity<UserLoginResDTO> loginUser(@RequestBody @Valid UserLoginReqDTO userLoginReqDto) {
        UserLoginResDTO savedUser = userService.loginUser(userLoginReqDto);
        return ApiResponseEntity.ok(CommonConstants.GLOBAL_SUCCESS_MSG,savedUser);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getMyInfo(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok("id :  " + userId);
    }

}
