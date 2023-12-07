package com.springbootstart.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudentCTLBoard is a Querydsl query type for StudentCTLBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentCTLBoard extends EntityPathBase<StudentCTLBoard> {

    private static final long serialVersionUID = -1541220653L;

    public static final QStudentCTLBoard studentCTLBoard = new QStudentCTLBoard("studentCTLBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> sbno = createNumber("sbno", Long.class);

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QStudentCTLBoard(String variable) {
        super(StudentCTLBoard.class, forVariable(variable));
    }

    public QStudentCTLBoard(Path<? extends StudentCTLBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentCTLBoard(PathMetadata metadata) {
        super(StudentCTLBoard.class, metadata);
    }

}

