package com.trillon.camp.campingHome.board.controller;
import com.trillon.camp.campingHome.board.dto.BoardForm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;




import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.trillon.camp.campingHome.board.FileStore;
import com.trillon.camp.campingHome.board.dto.Board;
import com.trillon.camp.campingHome.board.dto.BoardForm;
import com.trillon.camp.campingHome.board.dto.UploadFile;
import com.trillon.camp.campingHome.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


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
    public String saveFile(@RequestParam String title,
                            @RequestParam String text,
                            @RequestParam String hashtag,
                            @RequestParam("file") List<MultipartFile> files) throws IOException {
        BoardForm boardForm = new BoardForm();
        title = new String(title.getBytes("8859_1"),"utf-8");
        text = new String(text.getBytes("8859_1"),"utf-8");
        hashtag = new String(hashtag.getBytes("8859_1"),"utf-8");
        title= new String(title.getBytes("8859_1"),"utf-8");
        text= new String(text.getBytes("8859_1"),"utf-8");
        hashtag= new String(hashtag.getBytes("8859_1"),"utf-8");
        boardForm.setTitle(title);
        boardForm.setText(text);
        boardForm.setHashtag(hashtag);
        boardService.insertBoard(boardForm, files);
    public String saveFile(@ModelAttribute BoardForm boardForm,
    public String saveFile(
            @ModelAttribute BoardForm boardForm,
            @RequestParam("addItemName") List<String> addItemName,
            @RequestParam("file") List<MultipartFile> files) throws IOException, ParseException {

        boardService.insertBoard(boardForm,addItemName,files);
        return "redirect:/campingHome/boards";
    }

    @GetMapping("board/modify") // 게시판 수정 1-1
    public String modify(int bdIdx, Model model) {
        model.addAllAttributes(boardService.selectBoardByBdIdx(bdIdx));
        return "/campingHome/boardForm-modify";
    }

    @PostMapping("board/modify") // 게시판수정 1-2
    public String modify(@ModelAttribute BoardForm boardForm,
                            @RequestParam("addItemName") List<String> addItemName) throws ParseException, IOException {

        int bdIdx = boardForm.getBdIdx();
        boardService.updateBoard(boardForm,addItemName);
        return "redirect:/campingHome/board/"+bdIdx;
    }


    @GetMapping("boards") // 게시판 목록페이지 접속
    public String boards(Model model,@RequestParam(required = false,defaultValue = "1") int page){
      //  model.addAllAttributes(boardService.selectBoardList(page));
        model.addAllAttributes(boardService.selectBoardList(page));
        return "/campingHome/boards";
    }



    @GetMapping("board/{bdIdx}") // 게시판 상세페이지 접속
    public String boardDetail(@PathVariable("bdIdx") int bdIdx,Model model) {
        model.addAllAttributes(boardService.selectBoardByBdIdx(bdIdx));

        // 텍스트 개행 반영
        BoardForm board = (BoardForm) model.getAttribute("board");
        String text=  board.getText();
        text.replace("\r\n","<br>");


        // 댓글 가져오기

        List<Reply> replies = boardService.selectReplyAll(bdIdx);
        model.addAttribute("replies",replies);
        return "/campingHome/boardDetail";
    }

    @PostMapping("board/{bdIdx}")// 게시판에서 쓴 댓글 저장
    @ResponseBody
    public Reply saveReply(@PathVariable int bdIdx, Model model,
                           @RequestBody Reply reply){
        reply.setBdIdx(bdIdx);
        boardService.insertReply(reply);
        return reply;
    }


    @ResponseBody
    @GetMapping("/images/{gnIdx}/{fileName}")  // 이미지를 출력해주는 메서드
    public Resource downloadImage(@PathVariable Object fileName,
                                    @PathVariable int gnIdx) throws MalformedURLException {
        return new UrlResource("file:"+"C:/campingHome/"+gnIdx+"/"+ fileName);
    }

    @GetMapping("board/remove") // 게시판 삭제
    public String remove(@RequestParam int bdIdx) {
        System.out.println("remove : " + bdIdx);
        boardService.deleteBoard(bdIdx);
        return "redirect:/campingHome/boards";
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

