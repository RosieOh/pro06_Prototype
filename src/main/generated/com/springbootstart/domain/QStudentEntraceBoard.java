package com.springbootstart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudentEntraceBoard is a Querydsl query type for StudentEntraceBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentEntraceBoard extends EntityPathBase<StudentEntraceBoard> {

    private static final long serialVersionUID = 1533062579L;

    public static final QStudentEntraceBoard studentEntraceBoard = new QStudentEntraceBoard("studentEntraceBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> sebno = createNumber("sebno", Long.class);

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QStudentEntraceBoard(String variable) {
        super(StudentEntraceBoard.class, forVariable(variable));
    }

    public QStudentEntraceBoard(Path<? extends StudentEntraceBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentEntraceBoard(PathMetadata metadata) {
        super(StudentEntraceBoard.class, metadata);
    }

}

