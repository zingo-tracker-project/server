package com.tracker.server.service.user;


import com.tracker.server.dto.user.req.UserLoginReqDTO;
import com.tracker.server.dto.user.req.UserUpdateReqDTO;
import com.tracker.server.dto.user.res.UserInfoResDTO;
import com.tracker.server.dto.user.res.UserLoginResDTO;
import com.tracker.server.entity.user.User;
import com.tracker.server.repository.user.UserRepository;
import com.tracker.server.utils.*;
import com.tracker.server.utils.enums.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserLoginResDTO loginUser(UserLoginReqDTO userLoginReqDto) {

        String encryptedId = HashIdsUtil.encode(userLoginReqDto.getUserId());

        String userId = "";
        String userNm = "";
        Gender gender;
        String age_grp = "";

        // 있는 회원이면 로그인
        Optional<User> findUser = userRepository.findUserById(encryptedId);

        if (findUser.isPresent()) {
            userId = encryptedId;
            userNm = findUser.get().getUserNm();
            gender = findUser.get().getGender();
            age_grp = findUser.get().getAgeGrp();
        }
        else {
            // 없는 회원이면 회원가입
            User newUser = User.builder()
                    .userId(encryptedId)
                    .gender(userLoginReqDto.getGender())
                    .userNm(userLoginReqDto.getUserNm())
                    .ageGrp(userLoginReqDto.getAge_grp())
                    .build();

            User createdUser = userRepository.createUserCustom(newUser);

            userId = createdUser.getUserId();
            userNm = createdUser.getUserNm();
            gender = createdUser.getGender();
            age_grp = createdUser.getAgeGrp();
        }

        JwtResDTO jwt = jwtUtil.generateJwtToken(userId);

        UserLoginResDTO userLoginResDTO = UserLoginResDTO.builder()
                .userId(userId)
                .userNm(userNm)
                .age_grp(age_grp)
                .gender(gender)
                .jwt(jwt).
                build();

        return userLoginResDTO;
    }

    public UserInfoResDTO updateUser(UserUpdateReqDTO updateData) {
        User findUser = findUserById(updateData.getUserId());

        log.info("findUser : : " + findUser);

        // 업데이트
        findUser = userRepository.updateUserCustom(updateData)
                .orElseThrow(() -> new CustomException("존재하지 않는 유저입니다."));

        return UserInfoResDTO.fromEntity(findUser);
    }

    public User findUserById(String id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new CustomException("존재하지 않는 유저입니다."));
        return user;
    }

}
