package com.tracker.server.controller.task;

import com.tracker.server.dto.task.req.BigTaskReqDTO;
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
    @PostMapping("/big")
    public ResponseEntity<BigTaskResDTO> create(@RequestBody BigTaskReqDTO bigTask) {
        BigTaskResDTO created = bigTaskService.create(bigTask);
        return ResponseEntity.ok(created);
    }

    // // 큰 할 일 단일 조회(Big Task Id)
    // @GetMapping("/big-task/{id}")
    // public ResponseEntity<Optional<BigTask>> get(@PathVariable Long id) {
    //     Optional<BigTask> bigTask = bigTaskService.get(id);
    //     return ResponseEntity.ok(bigTask);
    // }

    // 큰 할 일들 조회(User Id)
    @GetMapping("/big/user/{userId}")
    public ResponseEntity<List<BigTaskResDTO>> getByUserId(@PathVariable String userId) {
        List<BigTaskResDTO> bigTasks = bigTaskService.getByUserId(userId);
        return ResponseEntity.ok(bigTasks);
    }

    // 전체 할 일 조회(큰할일들+작은할일들)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BigTaskResDTO>> getAllTasks(@PathVariable String userId) {
        List<BigTaskResDTO> tasks = bigTaskService.getAllTasksForUser(userId);
        return ResponseEntity.ok(tasks);
    } 

    // 단일 큰 할 일 + 작은 할 일들 조회
    @GetMapping("/big/{id}")
    public ResponseEntity<BigTaskResDTO> get(@PathVariable Long id) {
        BigTaskResDTO tasks = bigTaskService.get(id);
        return ResponseEntity.ok(tasks);
    } 

    // 큰 할 일 수정
    @PutMapping("/big/{id}")
    public ResponseEntity<BigTaskResDTO> update(
            @PathVariable Long id,
            @RequestBody BigTaskReqDTO updateData
    ) {
        BigTaskResDTO updated = bigTaskService.updateBigTask(id, updateData);
        return ResponseEntity.ok(updated);
    }

    // 큰 할 일 삭제(Big Task Id)
    @DeleteMapping("/big/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bigTaskService.deleteBigTask(id);
        return ResponseEntity.noContent().build();
    }

    // 큰 할 일 삭제(User Id)
    @DeleteMapping("/big/user/{userId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable String userId) {
        bigTaskService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    // 작은 할 일 생성(Big Task Id)
    @PostMapping("/big/{id}/small")
    public ResponseEntity<SmallTask> createSmallTask(@PathVariable Long id, @RequestBody SmallTask smallTask) {
        BigTaskResDTO bigTask = bigTaskService.get(id);
        smallTask.setBigTask(bigTask.toEntity());
        SmallTask created = bigTaskService.create(smallTask);
        return ResponseEntity.ok(created);
    }

    // 작은 할 일들 조회(Big Task Id)
    @GetMapping("/big/{id}/small")
    public ResponseEntity<List<SmallTask>> getSmallTasks(@PathVariable Long id) {
        List<SmallTask> smallTask = bigTaskService.getSmallTasks(id);
        return ResponseEntity.ok(smallTask);
    }

}
