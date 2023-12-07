package com.springbootstart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qna")
public class Qna extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qno")
    private Long qno;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    @Column(name = "writer", nullable = false, length = 50)
    private String writer;

    private LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name = "writer", referencedColumnName = "mno", insertable = false, updatable = false)
    private Member member;
}