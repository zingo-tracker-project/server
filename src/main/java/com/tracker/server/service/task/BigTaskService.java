package com.tracker.server.service.task;

import com.tracker.server.entity.task.BigTask;
import com.tracker.server.repository.task.BigTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BigTaskService {

    private final BigTaskRepository bigTaskRepository;

    public BigTask create(BigTask bigTask) {
        return bigTaskRepository.save(bigTask);
    }
}
