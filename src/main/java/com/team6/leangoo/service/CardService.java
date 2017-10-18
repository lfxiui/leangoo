package com.team6.leangoo.service;

import com.team6.leangoo.mapper.*;
import com.team6.leangoo.model.*;
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
    @Autowired
    private CardUserMapper cardUserMapper;

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
    public Integer addCardUser(CardUser cardUser){
        cardUserMapper.insert(cardUser);
        return cardUser.getId();
    }
    public Integer removeCardUser(CardUser cardUser){
        return cardUserMapper.delete(cardUser);
    }
}
