package com.tracker.server.entity.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @OneToMany(mappedBy = "bigTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SmallTask> smallTaskList = new ArrayList<>();;

    @Column(name="big_task_title", nullable = false, length = 50)
    private String bigTaskTitle;







}
