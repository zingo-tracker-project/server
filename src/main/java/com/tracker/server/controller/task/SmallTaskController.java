package com.tracker.server.controller.task;

import com.tracker.server.dto.task.req.SmallTaskReqDTO;
import com.tracker.server.dto.task.res.SmallTaskResDTO;
import com.tracker.server.entity.task.SmallTask;
import com.tracker.server.service.task.SmallTaskService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task/small")
@RequiredArgsConstructor
public class SmallTaskController {

    private final SmallTaskService smallTaskService;

    @PostMapping("/{id}")
    public ResponseEntity<SmallTaskResDTO> updateSmallTask(
        @PathVariable Long id,
        @RequestBody SmallTaskReqDTO updateData) {
    SmallTaskResDTO updated = smallTaskService.updateSmallTask(id, updateData);
    return ResponseEntity.ok(updated);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmallTask(@PathVariable Long id) {
        smallTaskService.deleteSmallTask(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable String userId) {
        smallTaskService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/big/{id}")
    public ResponseEntity<Void> deleteByBigTaskId(@PathVariable Long id) {
        smallTaskService.deleteByBigTaskId(id);
        return ResponseEntity.noContent().build();
    }

}
