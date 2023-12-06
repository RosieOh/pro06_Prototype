package com.springbootstart.dto.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnaDTO {
    private Long qno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
}
