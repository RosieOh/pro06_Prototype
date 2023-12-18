package com.springbootstart.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;

    @NotEmpty
    @Size(max = 200)
    private String title;

    @NotEmpty
    @Size(max = 2000)
    private String content;

    @NotEmpty
    @Size(max = 50)
    private String boardType;

    @NotEmpty
    @Size(max = 50)
    private String writer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private List<String> fileNames;
}
