package com.tracker.server.entity.task;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tracker.server.dto.task.res.BigTaskResDTO;

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
    @JsonBackReference
    private BigTask bigTask;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_dt")
    @CreationTimestamp
    private LocalDateTime createdDt;

    @Column(name = "is_done")
    private Boolean isDone = false;

    @Column(name = "done_dt")
    private LocalDateTime doneDt;

    @Column(name = "start_dt")
    private LocalDateTime startDt;

    @Column(name = "end_dt")
    private LocalDateTime endDt;

    @Column(name = "div_sug", nullable = false)
    private Boolean divSug = false;
    
}
