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

    /**
     * 로그인 및 회원가입
     * @param userLoginReqDto
     * @return
     */
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

    /**
     * 회원 업데이트
     * @param updateData
     * @return
     */
    public UserInfoResDTO updateUser(UserUpdateReqDTO updateData) {
        User findUser = findUserById(updateData.getUserId());

        // 업데이트
        findUser = userRepository.updateUserCustom(updateData).orElse(null);

        return UserInfoResDTO.fromEntity(findUser);
    }

    /**
     * 회원 찾기
     * @param userId
     * @return
     */
    public User findUserById(String userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new CustomException("존재하지 않는 유저입니다."));
        return user;
    }

    /**
     * refresh 토큰 발행
     * @param userId
     * @return
     */
    public JwtResDTO refreshToken(String userId) {
        JwtResDTO jwt = jwtUtil.generateJwtToken(userId);
        return jwt;
    }

}
