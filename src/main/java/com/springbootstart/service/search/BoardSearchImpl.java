package com.springbootstart.service.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.springbootstart.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public  BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(board.title.contains("11"));

        booleanBuilder.or(board.content.contains("11"));

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));
        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;

    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(board.bno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }

    @Override
    public Page<StudentCTLBoard> search2(Pageable pageable) {
        QStudentCTLBoard studentCTLBoard = QStudentCTLBoard.studentCTLBoard;

        JPQLQuery<StudentCTLBoard> query = from(studentCTLBoard);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(studentCTLBoard.title.contains("11"));

        booleanBuilder.or(studentCTLBoard.content.contains("11"));

        query.where(booleanBuilder);
        query.where(studentCTLBoard.sbno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<StudentCTLBoard> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<StudentCTLBoard> searchAll2(String[] types, String keyword, Pageable pageable) {

        QStudentCTLBoard studentCTLBoard = QStudentCTLBoard.studentCTLBoard;

        JPQLQuery<StudentCTLBoard> query = from(studentCTLBoard);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(studentCTLBoard.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(studentCTLBoard.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(studentCTLBoard.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(studentCTLBoard.sbno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<StudentCTLBoard> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<TeacherCTLBoard> search3(Pageable pageable) {
        QTeacherCTLBoard teacherCTLBoard = QTeacherCTLBoard.teacherCTLBoard;

        JPQLQuery<TeacherCTLBoard> query = from(teacherCTLBoard);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(teacherCTLBoard.title.contains("11"));

        booleanBuilder.or(teacherCTLBoard.content.contains("11"));

        query.where(booleanBuilder);
        query.where(teacherCTLBoard.tbno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<TeacherCTLBoard> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<TeacherCTLBoard> searchAll3(String[] types, String keyword, Pageable pageable) {

        QTeacherCTLBoard teacherCTLBoard= QTeacherCTLBoard.teacherCTLBoard;

        JPQLQuery<TeacherCTLBoard> query = from(teacherCTLBoard);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(teacherCTLBoard.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(teacherCTLBoard.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(teacherCTLBoard.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(teacherCTLBoard.tbno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<TeacherCTLBoard> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<StudentEntranceBoard> search4(Pageable pageable) {
        QStudentEntranceBoard studentEntranceBoard = QStudentEntranceBoard.studentEntranceBoard;

        JPQLQuery<StudentEntranceBoard> query = from(studentEntranceBoard);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(studentEntranceBoard.title.contains("11"));

        booleanBuilder.or(studentEntranceBoard.content.contains("11"));

        query.where(booleanBuilder);
        query.where(studentEntranceBoard.sebno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<StudentEntranceBoard> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<StudentEntranceBoard> searchAll4(String[] types, String keyword, Pageable pageable) {
        QStudentEntranceBoard studentEntranceBoard = QStudentEntranceBoard.studentEntranceBoard;

        JPQLQuery<StudentEntranceBoard> query = from(studentEntranceBoard);

        if ((types != null && types.length > 0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(studentEntranceBoard.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(studentEntranceBoard.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(studentEntranceBoard.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(studentEntranceBoard.sebno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<StudentEntranceBoard> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<TeacherEntranceBoard> search5(Pageable pageable) {
        QTeacherEntranceBoard teacherEntranceBoard = QTeacherEntranceBoard.teacherEntranceBoard;

        JPQLQuery<TeacherEntranceBoard> query = from(teacherEntranceBoard);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(teacherEntranceBoard.title.contains("11"));

        booleanBuilder.or(teacherEntranceBoard.content.contains("11"));

        query.where(booleanBuilder);
        query.where(teacherEntranceBoard.tebno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<TeacherEntranceBoard> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<TeacherEntranceBoard> searchAll5(String[] types, String keyword, Pageable pageable) {
        QTeacherEntranceBoard teacherEntranceBoard = QTeacherEntranceBoard.teacherEntranceBoard;

        JPQLQuery<TeacherEntranceBoard> query = from(teacherEntranceBoard);

        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(teacherEntranceBoard.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(teacherEntranceBoard.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(teacherEntranceBoard.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(teacherEntranceBoard.tebno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<TeacherEntranceBoard> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }


}
