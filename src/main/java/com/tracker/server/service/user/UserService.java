package com.tracker.server.service.user;


import com.tracker.server.dto.user.req.UserReqDTO;
import com.tracker.server.dto.user.res.UserResDTO;
import com.tracker.server.entity.user.User;
import com.tracker.server.repository.user.UserRepository;
import com.tracker.server.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserResDTO createUser(UserReqDTO userReqDto) {

        String encryptedId = HashIdsUtil.encode(userReqDto.getUserId());

        User user = User.builder()
                .userId(encryptedId)
                .email(userReqDto.getEmail())
                .name(userReqDto.getName())
                .build();

        User createdUser = userRepository.createUserCustom(user);
        JwtResDTO jwt = jwtUtil.generateJwtToken(createdUser.getUserId());

        UserResDTO userResDTO = UserResDTO.builder()
                .userId(createdUser.getUserId())
                .email(createdUser.getEmail())
                .name(createdUser.getName())
                .jwt(jwt).
                build();

        return userResDTO;
    }

    public User findUserById(String id) {
        User user = userRepository.findUserById(id);
        // TODO Custom Exception 구현 필요
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + id);
        }
        return user;
    }

    public void deleteUserById(String id) {
        userRepository.deleteUserById(id);
    }
}
