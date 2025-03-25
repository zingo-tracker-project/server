package com.tracker.server.repository.user;
import com.tracker.server.entity.user.User;

public interface UserRepositoryCustom {
    User findUserById(String id);
    void deleteUserById(String id);
    User createUserCustom(User user);
}
