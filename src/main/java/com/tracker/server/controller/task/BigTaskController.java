package com.tracker.server.controller.task;

import com.tracker.server.entity.task.BigTask;
import com.tracker.server.service.task.BigTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/big-task")
@RequiredArgsConstructor
public class BigTaskController {

    private final BigTaskService bigTaskService;

    @PostMapping
    public ResponseEntity<BigTask> create(@RequestBody BigTask bigTask) {
        BigTask created = bigTaskService.create(bigTask);
        return ResponseEntity.ok(created);
    }
}
