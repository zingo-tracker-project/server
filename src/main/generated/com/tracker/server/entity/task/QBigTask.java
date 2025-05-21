package com.tracker.server.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBigTask is a Querydsl query type for BigTask
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBigTask extends EntityPathBase<BigTask> {

    private static final long serialVersionUID = 133281823L;

    public static final QBigTask bigTask = new QBigTask("bigTask");

    public final NumberPath<Long> bigTaskId = createNumber("bigTaskId", Long.class);

    public final StringPath bigTaskTitle = createString("bigTaskTitle");

    public final ListPath<SmallTask, QSmallTask> smallTaskList = this.<SmallTask, QSmallTask>createList("smallTaskList", SmallTask.class, QSmallTask.class, PathInits.DIRECT2);

    public QBigTask(String variable) {
        super(BigTask.class, forVariable(variable));
    }

    public QBigTask(Path<? extends BigTask> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBigTask(PathMetadata metadata) {
        super(BigTask.class, metadata);
    }

}

