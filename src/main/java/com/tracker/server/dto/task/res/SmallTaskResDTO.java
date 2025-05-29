package com.tracker.server.dto.task.res;

import com.tracker.server.entity.task.SmallTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class SmallTaskResDTO {
    private Long smallTaskId;
    private String content;
    private Boolean isDone;
    private Boolean divSug;
    private LocalDateTime createdDt;
    private LocalDateTime doneDt;
}
