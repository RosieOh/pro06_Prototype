package com.springbootstart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeacherEntranceBoard is a Querydsl query type for TeacherEntranceBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacherEntranceBoard extends EntityPathBase<TeacherEntranceBoard> {

    private static final long serialVersionUID = 1035270400L;

    public static final QTeacherEntranceBoard teacherEntranceBoard = new QTeacherEntranceBoard("teacherEntranceBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> tebno = createNumber("tebno", Long.class);

    public final StringPath title = createString("title");

    public QTeacherEntranceBoard(String variable) {
        super(TeacherEntranceBoard.class, forVariable(variable));
    }

    public QTeacherEntranceBoard(Path<? extends TeacherEntranceBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeacherEntranceBoard(PathMetadata metadata) {
        super(TeacherEntranceBoard.class, metadata);
    }

}

