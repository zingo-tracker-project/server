package com.tracker.server.repository.task;

import com.tracker.server.entity.task.SmallTask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmallTaskRepository extends JpaRepository<SmallTask, Long> {

    List<SmallTask> findByBigTask_BigTaskId(Long bigTaskId);

    List<SmallTask> findByBigTask_User_UserId(String userId);

   
}