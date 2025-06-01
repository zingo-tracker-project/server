package com.tracker.server.service.task;

import com.tracker.server.dto.task.req.BigTaskReqDTO;
import com.tracker.server.dto.task.res.BigTaskResDTO;
import com.tracker.server.entity.task.BigTask;
import com.tracker.server.entity.task.SmallTask;
import com.tracker.server.entity.user.User;
import com.tracker.server.repository.task.BigTaskRepository;
import com.tracker.server.repository.task.SmallTaskRepository;
import com.tracker.server.repository.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BigTaskService {

    private final BigTaskRepository bigTaskRepository;
    private final SmallTaskRepository smallTaskRepository;
    private final UserRepository userRepository;

    // 큰 할 일 생성
    // public BigTaskResDTO create(BigTaskReqDTO bigTaskReq) {
    //     BigTask saved = bigTaskRepository.save(bigTaskReq.toEntity());
    //     return new BigTaskResDTO(saved);
    // }

    public BigTaskResDTO create(BigTaskReqDTO bigTaskReq) {
        User user = userRepository.findById(bigTaskReq.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        
        BigTask saved = bigTaskRepository.save(bigTaskReq.toEntity(user));
        return new BigTaskResDTO(saved);
    }

    // // 큰 할 일 단일 조회(Big Task Id)
    // public Optional<BigTask> get(Long id) {
    //     return bigTaskRepository.findById(id);
    // }

    // 큰 할 일들 조회(User Id)
    public List<BigTaskResDTO> getByUserId(String userId) {
        return bigTaskRepository.findByUser_UserId(userId).stream()
            .map(BigTaskResDTO::new)
            .collect(Collectors.toList());
    }

    // 전체 할 일 조회(큰할일+작은할일)
    public List<BigTaskResDTO> getAllTasksForUser(String userId) {
        return bigTaskRepository.findAllWithSmallTasksByUserId(userId).stream()
            .map(BigTaskResDTO::new)
            .collect(Collectors.toList());
    }

    // 단일 큰 할 일 + 작은 할 일들 조회
    public BigTaskResDTO get(Long id) {
        BigTask tasks = bigTaskRepository.findWithSmallTasksByBigTaskId(id)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 할 일이 없습니다."));
        return new BigTaskResDTO(tasks);
    }

    public BigTask getBigTask(Long id) {
        return bigTaskRepository.findWithSmallTasksByBigTaskId(id)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 할 일이 없습니다."));
    }

    // 큰 할 일 수정
    public BigTaskResDTO updateBigTask(Long id, BigTaskReqDTO updateData) {
        BigTask task = bigTaskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("할 일이 존재하지 않습니다"));

        task.setBigTaskTitle(updateData.getBigTaskTitle());
        task.setIsDone(updateData.getIsDone());
        task.setReminderAt(updateData.getReminderAt());
        task.setDoneDt(updateData.getDoneDt());
        task.setIsRepeat(updateData.getIsRepeat());

        BigTask saved = bigTaskRepository.save(task);
        return new BigTaskResDTO(saved);
    }

    // 큰 할일 삭제(Big Task Id)
    public void deleteBigTask(Long id) {
        BigTask task = bigTaskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("할 일이 존재하지 않습니다"));

        bigTaskRepository.delete(task);
    }

    // 큰 할 일 삭제(User Id)
    @Transactional
    public void deleteByUserId(String userId) { 
        List<BigTask> tasks = bigTaskRepository.findByUser_UserId(userId);
        bigTaskRepository.deleteAll(tasks);
    }

    // 작은 할 일 생성(Big Task Id)
    public SmallTask create(SmallTask smallTask) {
        return smallTaskRepository.save(smallTask);
    }


    // 작은 할 일 조회(Big Task Id)
    public List<SmallTask> getSmallTasks(Long id) {
        return smallTaskRepository.findByBigTask_BigTaskId(id);
    }

}
