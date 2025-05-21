package com.tracker.server.entity.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_SMALL_TASK")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmallTask {

    @Id
    @Column(name = "small_task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smallTaskId;

    @ManyToOne
    @JoinColumn(
            name = "big_task_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_smalltask_bigtask")
    )
    private BigTask bigTask;

}
