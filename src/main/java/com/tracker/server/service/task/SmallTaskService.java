package com.tracker.server.service.task;

import com.tracker.server.entity.task.SmallTask;
import com.tracker.server.repository.task.SmallTaskRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SmallTaskService {

    private final SmallTaskRepository bigTaskRepository;

    // // 작은 할일 생성
    // public SmallTask create(SmallTask bigTask) {
    //     return bigTaskRepository.save(bigTask);
    // }

    // // 작은 할일 조회(Small Task Id)
    // public Optional<SmallTask> get(Long id) {
    //     return bigTaskRepository.findById(id);
    // }

    // // 작은 할일 조회(User Id)
    // public List<SmallTask> getByUserId(String userId) {
    //     return bigTaskRepository.findByUser_UserId(userId);
    // }

    // // 작은 할일 수정
    // public SmallTask updateBigTask(Long id, SmallTask updateData) {
    //     SmallTask task = bigTaskRepository.findById(id)
    //         .orElseThrow(() -> new IllegalArgumentException("할 일이 존재하지 않습니다"));

    //     task.setBigTaskTitle(updateData.getBigTaskTitle());
    //     task.setIsDone(updateData.getIsDone());
    //     task.setReminderAt(updateData.getReminderAt());
    //     task.setDoneDt(updateData.getDoneDt());

    //     return bigTaskRepository.save(task);
    // }

    // // 작은 할일 삭제(Big Task Id)
    // public void deleteBigTask(Long id) {
    //     SmallTask task = bigTaskRepository.findById(id)
    //         .orElseThrow(() -> new IllegalArgumentException("할 일이 존재하지 않습니다"));

    //     bigTaskRepository.delete(task);
    // }

    // // 작은 할일 삭제(User Id)
    // @Transactional
    // public void deleteByUserId(String userId) { 
    //     List<SmallTask> tasks = bigTaskRepository.findByUser_UserId(userId);
    //     bigTaskRepository.deleteAll(tasks);
    // }
}
