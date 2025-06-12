package com.tracker.server.service.task;

import com.tracker.server.dto.task.req.SmallTaskReqDTO;
import com.tracker.server.dto.task.res.SmallTaskResDTO;
import com.tracker.server.entity.task.SmallTask;
import com.tracker.server.repository.task.SmallTaskRepository;
import com.tracker.server.utils.CustomException;

import com.tracker.server.utils.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SmallTaskService {

    private final SmallTaskRepository smallTaskRepository;


    // 작은 할일 수정
    @Transactional
    public SmallTaskResDTO updateSmallTask(Long id, SmallTaskReqDTO updateData) {
        SmallTask task = smallTaskRepository.findById(id)
            .orElseThrow(() -> new CustomException("작은 할 일이 존재하지 않습니다"));
    
        task.setContent(updateData.getContent());
        task.setDivSug(updateData.getDivSug());
    
        return new SmallTaskResDTO(smallTaskRepository.save(task));
    }


    // 큰 할일 삭제(Small Task Id)
    public void deleteSmallTask(Long id) {
        SmallTask task = smallTaskRepository.findById(id)
            .orElseThrow(() -> new CustomException("작은 할 일이 존재하지 않습니다"));

        smallTaskRepository.delete(task);
    }

    // 큰 할 일 삭제(User Id)
    @Transactional
    public void deleteByUserId(String userId) {
        List<SmallTask> tasks = smallTaskRepository.findByBigTask_User_UserId(userId);
        smallTaskRepository.deleteAll(tasks);
    }

    // 큰 할 일 삭제(Big Task Id)
    @Transactional
    public void deleteByBigTaskId(Long id) {
        List<SmallTask> tasks = smallTaskRepository.findByBigTask_BigTaskId(id);
        smallTaskRepository.deleteAll(tasks);
    }
}
