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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBigTask bigTask = new QBigTask("bigTask");

    public final NumberPath<Long> bigTaskId = createNumber("bigTaskId", Long.class);

    public final StringPath bigTaskTitle = createString("bigTaskTitle");

    public final DateTimePath<java.time.LocalDateTime> createdDt = createDateTime("createdDt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> doneDt = createDateTime("doneDt", java.time.LocalDateTime.class);

    public final BooleanPath isDone = createBoolean("isDone");

    public final BooleanPath isRepeat = createBoolean("isRepeat");

    public final DateTimePath<java.time.LocalDateTime> reminderAt = createDateTime("reminderAt", java.time.LocalDateTime.class);

    public final ListPath<SmallTask, QSmallTask> smallTaskList = this.<SmallTask, QSmallTask>createList("smallTaskList", SmallTask.class, QSmallTask.class, PathInits.DIRECT2);

    public final com.tracker.server.entity.user.QUser user;

    public QBigTask(String variable) {
        this(BigTask.class, forVariable(variable), INITS);
    }

    public QBigTask(Path<? extends BigTask> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBigTask(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBigTask(PathMetadata metadata, PathInits inits) {
        this(BigTask.class, metadata, inits);
    }

    public QBigTask(Class<? extends BigTask> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.tracker.server.entity.user.QUser(forProperty("user")) : null;
    }

}

