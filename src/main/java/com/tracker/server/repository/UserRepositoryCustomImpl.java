package com.tracker.server.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.tracker.server.entity.QUser;

import com.tracker.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<User> findByUsernameAndEmail(String username, String email) {
//        Quer user = QUser.user;
//
//        return queryFactory
//                .selectFrom(user)
//                .where(user.username.eq(username), user.email.eq(email))
//                .fetch();
        return null;
    }

    @Override
    public List<User> searchByUsername(String username) {
//        QUser user = QUser.user;
//        return queryFactory
//                .selectFrom(user)
//                .where(user.username.contains(username))
//                .fetch();
        return null;
    }
}
