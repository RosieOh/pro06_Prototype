package com.springbootstart.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "faq")
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fno")
    private Long fno;

    @Column(name = "question", nullable = false, length = 1000)
    private String question;

    @Column(name = "answer", nullable = false, length = 1000)
    private String answer;

    @Column(name = "cnt", nullable = false, columnDefinition = "int default 0")
    private int cnt;

}
