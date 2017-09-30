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
    private ListMapper listMapper;

    public Board getCardList(Integer boardId) {
        return boardMapper.getList(boardId);
    }

    public Integer changeCard(Card card) {
        return cardMapper.updateByPrimaryKeySelective(card);
    }

    public Card newCard(Card card) {
        if (CheckId.canInsert(listMapper, card.getCardListId())) {
            cardMapper.insert(card);
            return card;
        } else return null;

    }

    public Integer delCard(Card card) {
        return cardMapper.deleteByPrimaryKey(card);
    }

    public Integer updateCardList(java.util.List<List> lists) {

        for (List list : lists) {
            listMapper.updateByPrimaryKey(list);
            list.getCardList().forEach(card ->
                    cardMapper.updateByPrimaryKeySelective(card)
            );
        }
        return 1;
    }
}
