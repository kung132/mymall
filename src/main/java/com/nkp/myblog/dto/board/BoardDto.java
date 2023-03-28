package com.nkp.myblog.dto.board;

import com.nkp.myblog.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private String title;

    private String author;

    private String content;

    private Long count;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .author(author)
                .content(content)
                .count(count)
                .build();
    }
}
