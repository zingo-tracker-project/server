package com.tracker.server.repository.task;

import com.tracker.server.entity.task.BigTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BigTaskRepository extends JpaRepository<BigTask, Long> {
}
