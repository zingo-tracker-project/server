package com.tracker.server.repository.user;
import com.tracker.server.dto.user.req.UserUpdateReqDTO;
import com.tracker.server.entity.user.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findUserById(String id);
    void deleteUserById(String id);
    User createUserCustom(User user);
    Optional<User> updateUserCustom(UserUpdateReqDTO updateData);
}
