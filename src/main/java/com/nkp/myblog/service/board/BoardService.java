package com.nkp.myblog.service.board;

import com.nkp.myblog.domain.Board;
import com.nkp.myblog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void create(Board board) {
        boardRepository.save(board);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    public Page<Board> list(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public void update(Board board, Long id) {
        Board bd = boardRepository.findById(id).orElseThrow();
        bd.setId(id);
        bd.setTitle(board.getTitle());
        bd.setContent(board.getContent());
        bd.setCount(board.getCount());
        bd.setAuthor(board.getAuthor());

        boardRepository.save(bd);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public List<Page<Board>> findByTitle(Board board, Pageable pageable) {
       return boardRepository.findByTitleContaining(board.getTitle(), pageable);
    }
}
