package com.tracker.server.entity.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.tracker.server.entity.user.User;

@Entity
@Table(name = "TB_BIG_TASK")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BigTask {

    @Id
    @Column(name = "big_task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bigTaskId;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_bigtask_user")
    )
    private User user;

    @Column(name="big_task_title", nullable = false, length = 50)
    private String bigTaskTitle;

    @Column(name = "created_dt")
    @CreationTimestamp
    private LocalDateTime createdDt;

    @Column(name = "is_done" )
    private Boolean isDone = false;

    @Column(name = "done_dt")
    private LocalDateTime doneDt;

    @Column(name = "reminder_at")
    private LocalDateTime reminderAt;

    @Column(name = "is_repeat")
    private Boolean isRepeat;

    @Builder.Default
    @OneToMany(mappedBy = "bigTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SmallTask> smallTaskList = new ArrayList<>();

    







}
