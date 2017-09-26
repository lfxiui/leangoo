package com.team6.leangoo.service;

import com.team6.leangoo.mapper.BoardMapper;
import com.team6.leangoo.mapper.CardMapper;
import com.team6.leangoo.mapper.ProjectBoardMapper;
import com.team6.leangoo.mapper.ProjectMapper;
import com.team6.leangoo.model.Board;
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
        boardMapper.insertUseGeneratedKeys(board);
        Integer projectId = projectBoard.getProjectId();
        if (CheckId.canInsert(projectMapper, projectId)) {
            projectBoard.setBoardId(board.getBoardId());
            projectBoardMapper.insert(projectBoard);
            return board.getBoardId();
        } else return -1;
    }
    public Board getBoardById(Board board){
        return boardMapper.selectByPrimaryKey(board);
    }

}
