package com.tracker.server.repository.user;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tracker.server.entity.user.QUser;
import com.tracker.server.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory; // query-dsl
    private final EntityManager entityManager; // jpa
    QUser user = QUser.user; // query dsl

    @Override
    @Transactional
    public User createUserCustom(User user) {
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