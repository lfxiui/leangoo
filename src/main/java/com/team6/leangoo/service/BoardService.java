package com.team6.leangoo.service;

import com.team6.leangoo.mapper.*;
import com.team6.leangoo.model.Board;
import com.team6.leangoo.model.BoardList;
import com.team6.leangoo.model.ProjectBoard;
import com.team6.leangoo.model.User;
import com.team6.leangoo.util.CheckId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lfxiui
 * Date: 2017/9/13
 * Time: 10:10
 */
@Service
@Transactional
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private ProjectBoardMapper projectBoardMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private ListMapper listMapper;
    @Autowired private BoardListMapper boardListMapper;

    public List getArchiveBoardList(User user) {
        List<Board> boards = boardMapper.getArchiveBoards(user.getUserId());
        List list = new ArrayList();
        for (Board temp : boards) {
            Map map = new HashMap();
            map.put("boardId", temp.getBoardId());
            map.put("boardName", temp.getBoardName());
            list.add(map);
        }
        return list;
    }

    public Integer newBoard(Board board, ProjectBoard projectBoard) {
        boardMapper.insert(board);
        int boardId =board.getBoardId();
        com.team6.leangoo.model.List list1=new com.team6.leangoo.model.List();
        com.team6.leangoo.model.List list2=new com.team6.leangoo.model.List();
        com.team6.leangoo.model.List list3=new com.team6.leangoo.model.List();
        com.team6.leangoo.model.List list4=new com.team6.leangoo.model.List();
        list1.setListName("目标");
        list2.setListName("待办事项");
        list3.setListName("进行中");
        list4.setListName("已完成");
        list1.setListLocate(0);
        list2.setListLocate(1);
        list3.setListLocate(2);
        list4.setListLocate(3);
        listMapper.insertSelective(list1);
        listMapper.insertSelective(list2);
        listMapper.insertSelective(list3);
        listMapper.insertSelective(list4);
        BoardList boardList1=new BoardList();
        BoardList boardList2=new BoardList();
        BoardList boardList3=new BoardList();
        BoardList boardList4=new BoardList();
        boardList1.setBoardId(boardId);
        boardList1.setListId(list1.getListId());
        boardList2.setBoardId(boardId);
        boardList2.setListId(list2.getListId());
        boardList3.setBoardId(boardId);
        boardList3.setListId(list3.getListId());
        boardList4.setBoardId(boardId);
        boardList4.setListId(list4.getListId());
        boardListMapper.insert(boardList1);
        boardListMapper.insert(boardList2);
        boardListMapper.insert(boardList3);
        boardListMapper.insert(boardList4);
        projectBoard.setBoardId(boardId);
        projectBoardMapper.insert(projectBoard);
        return boardId;
    }
    public Board getBoardById(Board board){
        return boardMapper.selectByPrimaryKey(board);
    }
    public Integer updateBoard(Board board){
        return boardMapper.updateByPrimaryKeySelective(board);
    }

}
