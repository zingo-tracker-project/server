package com.tracker.server.service.user;


import ch.qos.logback.classic.Logger;
import com.tracker.server.dto.user.req.UserReqDTO;
import com.tracker.server.dto.user.res.UserResDTO;
import com.tracker.server.entity.user.User;
import com.tracker.server.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResDTO createUser(UserReqDTO userReqDto) {

        User user = User.builder()
                .id(userReqDto.getId()) // TODO ID값 양방향 임호화
                .email(userReqDto.getEmail())
                .name(userReqDto.getName())
                .build();

        User createdUser = userRepository.createUserCustom(user);

        UserResDTO userResDTO = UserResDTO.builder()
                .id(createdUser.getId())
                .email(createdUser.getEmail())
                .name(createdUser.getName()).
                build();

        return userResDTO;
    }

    public UserResDTO findUserById(String id) {

        User user = userRepository.findUserById(id);

        // TODO Custom Exception 구현 필요
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + id);
        }

        UserResDTO userResDTO = UserResDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName()).
                build();

        return userResDTO;
    }

    public UserResDTO updateUserById(UserResDTO userResDTO) {

        User user = User.builder()
                .id(userResDTO.getId())
                .email(userResDTO.getEmail())
                .name(userResDTO.getName())
                .build();

        User updatedUser = userRepository.save(user);

            UserResDTO updatedDTO = UserResDTO.builder()
                .id(updatedUser.getId())
                .email(updatedUser.getEmail())
                .name(updatedUser.getName()).
                build();

        return updatedDTO;
    }

    public void deleteUserById(String id) {

        User user = userRepository.findUserById(id);

        // TODO Custom Exception 구현 필요
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + id);
        }

        userRepository.deleteById(id);;
    }
}
