package com.tracker.server.controller.user;

import com.tracker.server.dto.user.req.UserReqDTO;
import com.tracker.server.dto.user.res.UserResDTO;
import com.tracker.server.entity.user.User;
import com.tracker.server.service.user.UserService;
import com.tracker.server.utils.ApiResponseEntity;
import com.tracker.server.utils.CommonConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/user") // User API
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO Filter를 이용한 로깅 및 보안처리 필요
    @PostMapping("")
    public ApiResponseEntity<UserResDTO> createUser(@RequestBody @Valid UserReqDTO userReqDto) {
        UserResDTO savedUser = userService.createUser(userReqDto);
        return ApiResponseEntity.ok(CommonConstants.GLOBAL_SUCCESS_MSG,savedUser);
    }

    // TODO 회원 정보 수정
    @PatchMapping("/{id}")
    public ApiResponseEntity<UserResDTO> updateUser(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        UserResDTO user = userService.findUserById(id);

        if (updates.containsKey("email")) {
            user.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("name")) {
            user.setName((String) updates.get("name"));
        }

        UserResDTO savedUser = userService.updateUserById(user);

        return ApiResponseEntity.ok(CommonConstants.GLOBAL_SUCCESS_MSG,savedUser);
    }

    // TODO 회원 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);;
        return ResponseEntity.noContent().build();  // 204 No Content 응답
    }

    // TODO 회원 정보 가져오기
    @GetMapping("/{id}")
    public ApiResponseEntity<UserResDTO> getUser(@PathVariable String id) {
        UserResDTO user = userService.findUserById(id);
        return ApiResponseEntity.ok(CommonConstants.GLOBAL_SUCCESS_MSG,user);
    }

}
