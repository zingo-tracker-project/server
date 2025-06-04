package com.tracker.server.repository.task;

import com.tracker.server.entity.task.BigTask;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BigTaskRepository extends JpaRepository<BigTask, Long> {

    List<BigTask> findByUser_UserId(String userId);

    @Query("SELECT b FROM BigTask b JOIN FETCH b.smallTaskList WHERE b.user.userId = :userId")
    List<BigTask> findAllWithSmallTasksByUserId(@Param("userId") String userId);

    @EntityGraph(attributePaths = {"smallTaskList"})
    Optional<BigTask> findWithSmallTasksByBigTaskId(Long bigTaskId);
}
