package com.team6.leangoo.service;

import com.team6.leangoo.mapper.BoardMapper;
import com.team6.leangoo.mapper.CardMapper;
import com.team6.leangoo.mapper.ListCardMapper;
import com.team6.leangoo.mapper.ListMapper;
import com.team6.leangoo.model.Board;
import com.team6.leangoo.model.Card;
import com.team6.leangoo.model.List;
import com.team6.leangoo.model.ListCard;
import com.team6.leangoo.util.CheckId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by AgZou on 2017/9/14.
 */
@Service
@Transactional
public class CardService {
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private ListCardMapper listCardMapper;
    @Autowired
    private ListMapper listMapper;

    public Board getCardList(Integer boardId) {
        return boardMapper.getList(boardId);
    }

    public Integer changeCard(Card card) {
        return cardMapper.updateByPrimaryKeySelective(card);
    }

    public Card newCard(Card card,ListCard listCard) {
        cardMapper.insert(card);
        listCard.setCardId(card.getCardId());
        if(CheckId.canInsert(listMapper,listCard.getListId()))
        listCardMapper.insert(listCard);
        else return null;
        return card;
    }
    public Integer delCard(Card card){
        if(card.getCardId()!=null) {
            Example example = new Example(ListCard.class);
            example.createCriteria().andEqualTo("cardId", card.getCardId());
            listCardMapper.deleteByExample(example);
            return cardMapper.deleteByPrimaryKey(card);
        }return -1;
    }
    public Integer updateCardList(Board board){
        for(com.team6.leangoo.model.List temp:board.getLists()){
            cardMapper.insertList(temp.getCardList());
        }return 1;
    }
}
