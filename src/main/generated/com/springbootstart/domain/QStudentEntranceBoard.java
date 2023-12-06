package com.springbootstart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudentEntranceBoard is a Querydsl query type for StudentEntranceBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentEntranceBoard extends EntityPathBase<StudentEntranceBoard> {

    private static final long serialVersionUID = 1457199943L;

    public static final QStudentEntranceBoard studentEntranceBoard = new QStudentEntranceBoard("studentEntranceBoardService");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> sebno = createNumber("sebno", Long.class);

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QStudentEntranceBoard(String variable) {
        super(StudentEntranceBoard.class, forVariable(variable));
    }

    public QStudentEntranceBoard(Path<? extends StudentEntranceBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentEntranceBoard(PathMetadata metadata) {
        super(StudentEntranceBoard.class, metadata);
    }

}

