package com.hongsi.mapleton.dto;

import com.hongsi.mapleton.entity.Board;
import com.hongsi.mapleton.entity.Hongsi;
import lombok.Data;

@Data
public class HongsiBoardDto {
    private Long board_id;
    private String image;
    private String content;

    public HongsiBoardDto(Board board) {
        this.board_id = board.getId();
        this.image = board.getImage();
        this.content = board.getContent();
    }
}
