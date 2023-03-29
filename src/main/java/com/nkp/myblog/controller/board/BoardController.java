package com.nkp.myblog.controller.board;

import com.nkp.myblog.domain.Board;
import com.nkp.myblog.dto.api.ApiResponse;
import com.nkp.myblog.dto.board.BoardDto;
import com.nkp.myblog.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ApiResponse<Board> create(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        boardService.create(board);
        return ApiResponse.<Board>builder()
                .data(board)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping
    public ApiResponse<List<Page<Board>>> findByTitle(BoardDto boardDto, Pageable pageable) {
        Board board = boardDto.toEntity();
        List<Page<Board>> result = boardService.findByTitle(board, pageable);
        return ApiResponse.<List<Page<Board>>> builder()
                .data(result)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Board> findById(@PathVariable Long id) {
        Board board = boardService.findById(id);
        return ApiResponse.<Board>builder()
                .data(board)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<Page<Board>> list(Pageable pageable) {
        Page<Board> board = boardService.list(pageable);
        return ApiResponse.<Page<Board>>builder()
                .data(board)
                .status(HttpStatus.OK)
                .build();
    }
    @PutMapping("/{id}")
    public void update(BoardDto boardDto, Long id) {
        Board board = boardDto.toEntity();
        boardService.update(board, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        boardService.delete(id);
    }
}
