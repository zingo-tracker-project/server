package com.tracker.server.dto.task.res;

import com.tracker.server.entity.task.SmallTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmallTaskResDTO {
    private Long smallTaskId;
    private String content;
    private Boolean isDone;
    private Boolean divSug;
    private LocalDateTime createdDt;
    private LocalDateTime doneDt;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

    public SmallTaskResDTO(SmallTask smallTask) {
        this.smallTaskId = smallTask.getSmallTaskId();
        this.content = smallTask.getContent();
        this.isDone = smallTask.getIsDone();
        this.divSug = smallTask.getDivSug();
        this.createdDt = smallTask.getCreatedDt();
        this.doneDt = smallTask.getDoneDt();
        this.startDt = smallTask.getStartDt();
        this.endDt = smallTask.getEndDt();
    }
}
