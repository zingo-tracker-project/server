package com.tracker.server.repository;

import com.tracker.server.entity.User;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepositoryCustom {
    List<User> findByUsernameAndEmail(String username, String email);
    List<User> searchByUsername(String username);
}
