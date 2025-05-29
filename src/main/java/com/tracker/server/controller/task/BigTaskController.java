package com.tracker.server.controller.task;

import com.tracker.server.dto.task.res.BigTaskResDTO;
import com.tracker.server.entity.task.BigTask;
import com.tracker.server.entity.task.SmallTask;
import com.tracker.server.repository.task.SmallTaskRepository;
import com.tracker.server.service.task.BigTaskService;
import com.tracker.server.service.task.SmallTaskService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class BigTaskController {

    private final BigTaskService bigTaskService;

    // 큰 할 일 생성
    @PostMapping("/big-task")
    public ResponseEntity<BigTask> create(@RequestBody BigTask bigTask) {
        BigTask created = bigTaskService.create(bigTask);
        return ResponseEntity.ok(created);
    }

    // // 큰 할 일 단일 조회(Big Task Id)
    // @GetMapping("/big-task/{id}")
    // public ResponseEntity<Optional<BigTask>> get(@PathVariable Long id) {
    //     Optional<BigTask> bigTask = bigTaskService.get(id);
    //     return ResponseEntity.ok(bigTask);
    // }

    // 큰 할 일들 조회(User Id)
    @GetMapping("/big-task/user/{userId}")
    public ResponseEntity<List<BigTask>> get(@PathVariable String userId) {
        List<BigTask> bigTasks = bigTaskService.getByUserId(userId);
        return ResponseEntity.ok(bigTasks);
    }

    // 전체 할 일 조회(큰할일들+작은할일들)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BigTask>> getAllTasks(@PathVariable String userId) {
        List<BigTask> tasks = bigTaskService.getAllTasksForUser(userId);
        return ResponseEntity.ok(tasks);
    } 

    // 단일 큰 할 일 + 작은 할 일들 조회
    @GetMapping("/big-task/{id}")
    public ResponseEntity<BigTaskResDTO> get(@PathVariable Long id) {
        BigTaskResDTO tasks = bigTaskService.get(id);
        return ResponseEntity.ok(tasks);
    } 

    // 큰 할 일 수정
    @PutMapping("/big-task/{id}")
    public ResponseEntity<BigTask> update(
            @PathVariable Long id,
            @RequestBody BigTask updateData
    ) {
        BigTask updated = bigTaskService.updateBigTask(id, updateData);
        return ResponseEntity.ok(updated);
    }

    // 큰 할 일 삭제(Big Task Id)
    @DeleteMapping("/big-task/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bigTaskService.deleteBigTask(id);
        return ResponseEntity.noContent().build();
    }

    // 큰 할 일 삭제(User Id)
    @DeleteMapping("/big-task/user/{userId}")
    public ResponseEntity<Void> delete(@PathVariable String userId) {
        bigTaskService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    // 작은 할 일 생성(Big Task Id)
    @PostMapping("/big-task/{id}/small-task")
    public ResponseEntity<SmallTask> create(@PathVariable Long id, @RequestBody SmallTask smallTask) {
        BigTask bigTask = bigTaskService.getBigTask(id);
        smallTask.setBigTask(bigTask);
        SmallTask created = bigTaskService.create(smallTask);
        return ResponseEntity.ok(created);
    }

    // 작은 할 일들 조회(Big Task Id)
    @GetMapping("/big-task/{id}/small-tasks")
    public ResponseEntity<List<SmallTask>> getSmallTasks(@PathVariable Long id) {
        List<SmallTask> smallTask = bigTaskService.getSmallTasks(id);
        return ResponseEntity.ok(smallTask);
    }

}
