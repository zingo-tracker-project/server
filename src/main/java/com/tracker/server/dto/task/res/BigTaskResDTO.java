package com.tracker.server.dto.task.res;

import com.tracker.server.dto.task.res.SmallTaskResDTO;
import com.tracker.server.entity.task.BigTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BigTaskResDTO {
    public BigTaskResDTO(BigTask tasks) {
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
