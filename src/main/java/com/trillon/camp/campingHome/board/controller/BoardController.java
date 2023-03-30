package com.trillon.camp.campingHome.board.controller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.trillon.camp.campingHome.board.dto.BoardForm;
import com.trillon.camp.campingHome.board.dto.Reply;
import com.trillon.camp.campingHome.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


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
    public String saveFile(
    						@RequestParam String title,
                            @RequestParam String text,
                            @RequestParam String hashtag,
                            @RequestParam("file") List<MultipartFile> files) throws IOException {
        BoardForm boardForm = new BoardForm();
        title= new String(title.getBytes("8859_1"),"utf-8");
        text= new String(text.getBytes("8859_1"),"utf-8");
        hashtag= new String(hashtag.getBytes("8859_1"),"utf-8");
        boardForm.setTitle(title);
        boardForm.setText(text);
        boardForm.setHashtag(hashtag);
        boardService.insertBoard(boardForm, files);
        System.out.println(boardForm);
        return "redirect:/campingHome/boards";
    }

    @GetMapping("boards") // 게시판 목록페이지 접속
    public String boards(Model model,@RequestParam(required = false,defaultValue = "1") int page){
        model.addAllAttributes(boardService.selectBoardList(page));
        return "/campingHome/boards";
    }

    @GetMapping("/board/{bdIdx}") // 게시판 상세페이지 접속
    public String boardDetail(@PathVariable("bdIdx") int bdIdx,Model model) {
        model.addAllAttributes(boardService.selectBoardByBdIdx(bdIdx));

        // 댓글 가져오기
        List<Reply> replies = boardService.selectReplyAll(bdIdx);
        model.addAttribute("replies",replies);
        return "/campingHome/boardDetail";
    }

    @PostMapping("/board/{bdIdx}")// 게시판에서 쓴 댓글 저장
    @ResponseBody
    public void saveReply(@PathVariable("bdIdx") int bdIdx, Model model,
                            @RequestBody Reply reply){
        boardService.insertReply(reply);
    }


    @ResponseBody
    @GetMapping("/images/{gnIdx}/{fileName}")  // 이미지를 출력해주는 메서드
    public Resource downloadImage(@PathVariable Object fileName,
                                    @PathVariable int gnIdx) throws MalformedURLException {
                return new UrlResource("file:"+"C:/campingHome/"+gnIdx+"/"+ fileName);
    }

}

