package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends JpaRepository<Board, Long> {
    Board findByBoardName(String boardName);
    boolean existsBoardByBoardName(String boardName);
}
