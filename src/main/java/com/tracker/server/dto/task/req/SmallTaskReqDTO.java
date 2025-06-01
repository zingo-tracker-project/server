package com.tracker.server.dto.task.req;

import com.tracker.server.entity.task.SmallTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmallTaskReqDTO {

    private String content;
    private Boolean divSug = false;

    public SmallTask toEntity() {
        return SmallTask.builder()
            .content(this.content)
            .divSug(this.divSug)
            .build();
    }

    public SmallTask toEntityWithId(Long id) {
        return SmallTask.builder()
            .smallTaskId(id)
            .content(this.content)
            .divSug(this.divSug)
            .build();
    }
}
