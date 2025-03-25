package com.tracker.server.service.user;


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
                .id(userReqDto.getId())
                .email(userReqDto.getEmail())
                .name(userReqDto.getName())
                .build();

        User createdUser = userRepository.save(user);

        UserResDTO userResDTO = UserResDTO.builder()
                .id(createdUser.getId())
                .email(createdUser.getEmail())
                .name(createdUser.getName()).
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
