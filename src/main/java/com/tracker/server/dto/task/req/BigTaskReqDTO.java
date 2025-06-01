package com.tracker.server.dto.task.req;

import com.tracker.server.entity.task.BigTask;
import com.tracker.server.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BigTaskReqDTO {
    private String bigTaskTitle;
    private Boolean isDone;
    private LocalDateTime reminderAt;
    private LocalDateTime doneDt;
    private Boolean isRepeat;
    private String userId;

    public BigTask toEntity(User user) {
        return BigTask.builder()
            .bigTaskTitle(this.bigTaskTitle)
            .isDone(this.isDone)
            .reminderAt(this.reminderAt)
            .isRepeat(this.isRepeat)
            .user(user)
            .build();
    }
}
