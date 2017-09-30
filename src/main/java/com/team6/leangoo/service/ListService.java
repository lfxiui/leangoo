package com.team6.leangoo.service;

import com.team6.leangoo.mapper.*;
import com.team6.leangoo.model.*;
import com.team6.leangoo.util.CheckId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class ListService {
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private BoardListMapper boardListMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private ListCardMapper listCardMapper;


    public Board newList(List list, BoardList boardList) {
        listMapper.insert(list);
        Integer boardId = boardList.getBoardId();
        if (CheckId.canInsert(boardMapper, boardId)) {
            boardList.setListId(list.getListId());
            boardListMapper.insert(boardList);
            return boardMapper.getList(boardId);
        } else return null;
    }

    public Integer delList(List list,Board board) {
        if(list!=null&&list.getListId()!=null){
        BoardList boardList = new BoardList();
        Example example = new Example(Card.class);
        example.createCriteria().andEqualTo("cardListId", list.getListId());
        boardList.setListId(list.getListId());
        boardList.setBoardId(board.getBoardId());
        boardListMapper.delete(boardList);
        cardMapper.deleteByExample(example);
        }
        return listMapper.deleteByPrimaryKey(list);
    }
    public Integer updateList(List list){
        return listMapper.updateByPrimaryKeySelective(list);
    }
}
