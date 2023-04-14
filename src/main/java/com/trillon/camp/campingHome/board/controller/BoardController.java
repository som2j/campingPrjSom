package com.trillon.camp.campingHome.board.controller;
import com.trillon.camp.campingHome.board.dto.BoardForm;
import com.trillon.camp.campingHome.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.trillon.camp.campingHome.board.FileStore;
import com.trillon.camp.campingHome.board.dto.Board;
import com.trillon.camp.campingHome.board.dto.BoardForm;
import com.trillon.camp.campingHome.board.dto.UploadFile;
import com.trillon.camp.campingHome.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.io.IOException;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/campingHome")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("board/new") // 게시판 등록 폼
    public String newFile(){
        return "/campingHome/board-form";
    }

    @PostMapping("board/new")// 게시판 등록 버튼을 눌렀을 때 실행되는 메서드
    @ResponseBody
    public void saveFile(@RequestBody BoardForm boardForm) throws IOException {
        boardService.insertBoard(boardForm);


    }

    @GetMapping("boards") // 게시판 목록페이지 접속
    public String boards(Model model,@RequestParam(required = false,defaultValue = "1") int page){
      //  model.addAllAttributes(boardService.selectBoardList(page));
        model.addAllAttributes(boardService.selectBoardList(page));
        return "/campingHome/boards";
    }

    @GetMapping("/board/{bdIdx}") // 게시판 상세페이지 접속
    public String boardDetail(@PathVariable("bdIdx") int bdIdx,Model model) {
        System.out.println("getMapping");
        BoardForm boardForm = boardService.selectBoardByBdIdx(bdIdx);
        model.addAttribute("board",boardForm);
        return "/campingHome/boardDetail";
    }



}


public class BoardController {

    private final BoardRepository boardRepository;
    private final FileStore fileStore;

//    @GetMapping("/boards/new") // 게시판 등록 폼
//    public String newFile(@ModelAttribute BoardForm Form){
//        System.out.println("Get mapping 실행");
//        return "/campingHome/board-form";
//    }

    @GetMapping("/boards/new") // 게시판 등록 폼
    public String newFile(){
        System.out.println("Get mapping");
        return "/campingHome/board-form";
    }

    @PostMapping("boards/new")// 게시판 등록 버튼을 눌렀을 때 실행되는 메서드
    public String saveFile(@ModelAttribute BoardForm form, RedirectAttributes redirectAttributes,@RequestBody BoardForm boardForm) throws IOException {
        //List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        System.out.println("Post mapping");
        System.out.println(boardForm);

    // 데이터베이스에 저장
    Board board = new Board();
    board.setBd_idk(form.getBd_idk());
    board.setId(form.getId());
    board.setTitle(form.getTitle());
    board.setText(form.getText());
    //board.setImageFiles(storeImageFiles);
    boardRepository.save(board);

    redirectAttributes.addAttribute("bd_idk",board.getBd_idk());
        return "redirect:/campingHome/boards/{bd_idk}";
        //return "/campingHome/boardDetail";
    }

    //@PostMapping("/board/new")
    public String saveFile(@RequestBody BoardForm form){
        System.out.println("postMapping 실행");
        System.out.println("넘어온 값" + form);
        return "/campingHome/boards/{bd_idk}";
    }

    @GetMapping("/boards/{bd_idk}") // 게시판 조회
    public String boardDetail(@PathVariable long bd_idk, Model model) {
        Board board = boardRepository.findByBd_idk(bd_idk);
        model.addAttribute("board",board);
        return "/campingHome/boardDetail";
    }
}

