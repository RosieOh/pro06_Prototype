package com.springbootstart.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeacherCTLBoard is a Querydsl query type for TeacherCTLBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacherCTLBoard extends EntityPathBase<TeacherCTLBoard> {

    private static final long serialVersionUID = 396462458L;

    public static final QTeacherCTLBoard teacherCTLBoard = new QTeacherCTLBoard("teacherCTLBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> tbno = createNumber("tbno", Long.class);

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QTeacherCTLBoard(String variable) {
        super(TeacherCTLBoard.class, forVariable(variable));
    }

    public QTeacherCTLBoard(Path<? extends TeacherCTLBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeacherCTLBoard(PathMetadata metadata) {
        super(TeacherCTLBoard.class, metadata);
    }

}

