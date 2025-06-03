package com.tracker.server.repository.user;


import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tracker.server.dto.user.req.UserUpdateReqDTO;
import com.tracker.server.entity.user.QUser;
import com.tracker.server.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory; // query-dsl
    private final EntityManager entityManager; // jpa
    QUser qUser = QUser.user; // query dsl

    @Override
    @Transactional
    public User createUserCustom(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public Optional<User> updateUserCustom(UserUpdateReqDTO updateData) {

        queryFactory.update(qUser)
                .set(qUser.profileImage, updateData.getProfileImage())
                .set(qUser.userNm, updateData.getUserNm())
                .set(qUser.gender, updateData.getGender())
                .set(qUser.ageGrp, updateData.getAgeGrp())
                .where(qUser.userId.eq(updateData.getUserId()))
                .execute();

        entityManager.clear();
        return findUserById(updateData.getUserId());
    }


    @Override
    public Optional<User> findUserById(String userId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qUser)
                .where(qUser.userId.eq(userId))
                .fetchOne());
    }
    @Override
    @Transactional
    public void deleteUserById(String userId) {
        queryFactory.delete(qUser)
                .where(qUser.userId.eq(userId))
                .execute();
    }



}