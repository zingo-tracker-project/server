package com.tracker.server.repository.user;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tracker.server.entity.user.QUser;
import com.tracker.server.entity.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    QUser user = QUser.user;

    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findUserById(String id) {
        return queryFactory
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne();
    }
    @Override
    public void deleteUserById(String id) {
        queryFactory.delete(user)
                .where(user.id.eq(id))
                .execute();
    }

}