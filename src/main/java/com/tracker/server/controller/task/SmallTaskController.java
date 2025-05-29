package com.tracker.server.controller.task;

import com.tracker.server.entity.task.SmallTask;
import com.tracker.server.service.task.SmallTaskService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/small-task")
@RequiredArgsConstructor
public class SmallTaskController {

    private final SmallTaskService smallTaskService;

    // @PostMapping
    // public ResponseEntity<SmallTask> create(@RequestBody SmallTask bigTask) {
    //     SmallTask created = smallTaskService.create(bigTask);
    //     return ResponseEntity.ok(created);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Optional<SmallTask>> get(@PathVariable Long id) {
    //     Optional<SmallTask> smallTask = smallTaskService.get(id);
    //     return ResponseEntity.ok(smallTask);
    // }

//     @GetMapping("/user/{userId}")
//     public ResponseEntity<List<SmallTask>> get(@PathVariable String userId) {
//         List<SmallTask> smallTasks = smallTaskService.getByUserId(userId);
//         return ResponseEntity.ok(smallTasks);
//     }


//     @PostMapping("/{id}")
//     public ResponseEntity<SamllTask> update(
//             @PathVariable Long id,
//             @RequestBody SmallTask updateData
//     ) {
//         SmallTask updated = smallTaskService.updateSmallTask(id, updateData);
//         return ResponseEntity.ok(updated);
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> delete(@PathVariable Long id) {
//         smallTaskService.deleteSmallTask(id);
//         return ResponseEntity.noContent().build();
//     }

    
//     @DeleteMapping("/user/{userId}")
//     public ResponseEntity<Void> delete(@PathVariable String userId) {
//         smallTaskService.deleteByUserId(userId);
//         return ResponseEntity.noContent().build();
//     }
}
