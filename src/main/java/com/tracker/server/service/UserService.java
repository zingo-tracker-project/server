package com.tracker.server.service;


import com.tracker.server.dto.UserDto;
import com.tracker.server.entity.User;
import com.tracker.server.repository.UserRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDto userDto) {

        String id = userDto.getId();

        User user = User.builder()
                .id(id)
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();

        return userRepository.save(user);
    }
}
