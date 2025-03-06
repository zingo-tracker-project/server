package com.tracker.server.service;


import com.tracker.server.dto.UserDto;
import com.tracker.server.entity.User;
import com.tracker.server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDto userDto) {

        String id = userDto.getId();

        User user = new User(id, userDto.getEmail(), userDto.getName());

        return userRepository.save(user);
    }
}
