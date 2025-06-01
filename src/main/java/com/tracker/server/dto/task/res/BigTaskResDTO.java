package com.tracker.server.dto.task.res;

import com.tracker.server.dto.task.res.SmallTaskResDTO;
import com.tracker.server.entity.task.BigTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class BigTaskResDTO {
    public BigTaskResDTO(BigTask bigTask) {
        this.bigTaskId = bigTask.getBigTaskId();
        this.bigTaskTitle = bigTask.getBigTaskTitle();
        this.isDone = bigTask.getIsDone();
        this.reminderAt = bigTask.getReminderAt();
        this.doneDt = bigTask.getDoneDt();
        this.createdDt = bigTask.getCreatedDt();
        this.isRepeat = bigTask.getIsRepeat();
        this.smallTasks = bigTask.getSmallTaskList().stream()
            .map(SmallTaskResDTO::new)
            .collect(Collectors.toList());
    }

    public BigTask toEntity() {
        return BigTask.builder()
            .bigTaskId(this.bigTaskId)
            .bigTaskTitle(this.bigTaskTitle)
            .isDone(this.isDone)
            .reminderAt(this.reminderAt)
            .doneDt(this.doneDt)
            .createdDt(this.createdDt)
            .isRepeat(this.isRepeat)
            .build();
    }

    private Long bigTaskId;
    private String bigTaskTitle;
    private Boolean isDone;
    private LocalDateTime reminderAt;
    private LocalDateTime doneDt;
    private LocalDateTime createdDt;
    private Boolean isRepeat;
    private List<SmallTaskResDTO> smallTasks;
}
