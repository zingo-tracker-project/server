package com.tracker.server.service.user;


import com.tracker.server.dto.user.req.UserLoginReqDTO;
import com.tracker.server.dto.user.req.UserUpdateReqDTO;
import com.tracker.server.dto.user.res.UserInfoResDTO;
import com.tracker.server.dto.user.res.UserLoginResDTO;
import com.tracker.server.entity.user.User;
import com.tracker.server.repository.user.UserRepository;
import com.tracker.server.utils.*;
import org.springframework.beans.BeanUtils;
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
     * 카카오 로그인
     * @param userLoginReqDto
     * @return
     */
    public UserLoginResDTO loginUser(UserLoginReqDTO userLoginReqDto) {

        // 최초 카카오로그인에서 인코딩 되는 값으로 보내니, 있는 회원이라도 인코당 걸려서 나옴 서버로직 수정필요
        String encryptedId = "";
        boolean isNewUser = false;
        User user;

        Optional OptEncryptedId = HashIdsUtil.encode(userLoginReqDto.getUserId());
        encryptedId = (String) OptEncryptedId.get();

        Optional<User> findUser = userRepository.findUserById(encryptedId);
        if (findUser.isPresent()) {
            user = findUser.get();
        }else {
            // 없으면 회원가입
            User newUser = User.builder()
                    .userId(encryptedId)
                    .gender(userLoginReqDto.getGender())
                    .userNm(userLoginReqDto.getUserNm())
                    .profileImage(userLoginReqDto.getProfileImage())
                    .build();

            user = userRepository.createUserCustom(newUser);
            isNewUser = true;
        }

        JwtResDTO jwt = jwtUtil.generateJwtToken(encryptedId);

        UserLoginResDTO userLoginResDTO = UserLoginResDTO.builder()
                .userId(user.getUserId())
                .userNm(user.getUserNm())
                .gender(user.getGender())
                .ageGrp(user.getAgeGrp())
                .profileImage(user.getProfileImage())
                .isNewUser(isNewUser)
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

        // 업데이트
        User findUser = userRepository.updateUserCustom(updateData).orElseThrow(()-> {
            new CustomException("없는 회원입니다.");
            return null;
        });

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
