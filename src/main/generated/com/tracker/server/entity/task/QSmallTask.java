package com.tracker.server.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSmallTask is a Querydsl query type for SmallTask
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSmallTask extends EntityPathBase<SmallTask> {

    private static final long serialVersionUID = 915406374L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSmallTask smallTask = new QSmallTask("smallTask");

    public final QBigTask bigTask;

    public final NumberPath<Long> smallTaskId = createNumber("smallTaskId", Long.class);

    public QSmallTask(String variable) {
        this(SmallTask.class, forVariable(variable), INITS);
    }

    public QSmallTask(Path<? extends SmallTask> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSmallTask(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSmallTask(PathMetadata metadata, PathInits inits) {
        this(SmallTask.class, metadata, inits);
    }

    public QSmallTask(Class<? extends SmallTask> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bigTask = inits.isInitialized("bigTask") ? new QBigTask(forProperty("bigTask")) : null;
    }

}

