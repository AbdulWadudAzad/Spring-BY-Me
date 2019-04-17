package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Board;
import com.azad.templatequickjob.repo.BoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/board/")
public class BoardController {

    @Autowired
    private BoardRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("board", new Board());
        return "boards/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Board board, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "boards/add";
        }
        if (repo.existsBoardByBoardName(board.getBoardName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            board.setBoardName(board.getBoardName());
            this.repo.save(board);
            model.addAttribute("board",new Board());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "boards/add";
    }

    @GetMapping("edit/{id}")
    public String editboardView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", this.repo.getOne(id));
        return "boards/edit";
    }

    @PostMapping("edit/{id}")
    public String editboard(@Valid Board board, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "boards/edit";
        } else {
            if (board != null) {
               // Optional<Board> board1 = this.repo.findByBoardName(board.getBoardName());

                Board board1 = this.repo.findByBoardName(board.getBoardName());
                if (board1 != null) {
                    model.addAttribute("existMSG", "Board is exist");
                    return "boards/edit";
                } else {
                    board.setBoardName(board.getBoardName());

                    this.repo.save(board);
                    model.addAttribute("board", new Board());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/board/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/board/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "boards/list";
    }


}
