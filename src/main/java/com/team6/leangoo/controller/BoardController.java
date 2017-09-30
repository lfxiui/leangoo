package com.team6.leangoo.controller;

import com.team6.leangoo.model.Board;
import com.team6.leangoo.model.ProjectBoard;
import com.team6.leangoo.model.User;
import com.team6.leangoo.service.BoardService;
import com.team6.leangoo.service.UserService;
import com.team6.leangoo.util.AjaxResult;
import com.team6.leangoo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lfxiui
 * Date: 2017/9/13
 * Time: 10:17
 */
@RestController
@RequestMapping("/Board")
public class BoardController {
    final
    private BoardService boardService;
    final
    private UserService userService;

    @Autowired
    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getArchiveBoardList",method = RequestMethod.POST)
    public AjaxResult getArchiveBoardList(){
        Integer userId = 1;
        User user = new User();
        user.setUserId(userId);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            ajaxResult.setData(boardService.getArchiveBoardList(user));
            ajaxResult.seterrcode(0);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }
    @RequestMapping(value = "/getUserPersonalBoardList",method = RequestMethod.POST)
    public AjaxResult getUserPersonalBoardList(){
        Integer userId=1;
        AjaxResult ajaxResult=new AjaxResult();
        try {
            User user=userService.selectUserPersonalBoardList(userId);
            if(user!=null)
            ajaxResult.setData(user.getPersonalBoardList());
            else ajaxResult.seterrcode(AjaxResult.ERRCODE_NOT_EXITS);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
        }finally {
            return ajaxResult;
        }
    }
    @RequestMapping(value = "/newBoard",method = RequestMethod.POST,produces = "*/*")
    public AjaxResult newBoard(Board board,ProjectBoard projectBoard){
        board.setBoardIsArchive(0);
        board.setBoardStartDate(DateUtil.LocalDateToDate(LocalDate.now()));
        board.setBoardEndDate(DateUtil.LocalDateToDate(LocalDate.now().plusDays(7)));
        return new AjaxResult(boardService.newBoard(board,projectBoard));


    }
    @RequestMapping(value = "/getBoardById",method = RequestMethod.POST)
    public AjaxResult getBoardById(@RequestBody Board board){
       return new AjaxResult(boardService.getBoardById(board));
    }
    @RequestMapping(value = "/updateBoard",method = RequestMethod.POST)
    public AjaxResult updateBoard(@RequestBody Board board){
        return new AjaxResult(boardService.updateBoard(board));
    }
}
