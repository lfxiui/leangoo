package com.team6.leangoo.controller;

import com.team6.leangoo.model.Board;
import com.team6.leangoo.model.Card;
import com.team6.leangoo.model.ListCard;
import com.team6.leangoo.service.CardService;
import com.team6.leangoo.util.AjaxResult;
import com.team6.leangoo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by AgZou on 2017/9/14.
 */
@RestController
@RequestMapping("/Card")
public class CardController {
    @Autowired private CardService cardService;

    @RequestMapping(value = "/getCardList", method = RequestMethod.POST)
    public AjaxResult getCardList(@RequestBody Board board) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            Board board1=cardService.getCardList(board.getBoardId());
            if(board1!=null)
            ajaxResult.setData(board1.getLists());
            else ajaxResult.seterrcode(AjaxResult.ERRCODE_NOT_EXITS);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(AjaxResult.ERRCODE_SYSTEM_ERROR);
            ajaxResult.setinfo("请求失败");
        } finally {
            return ajaxResult;
        }
    }
    @RequestMapping(value = "/changeCard",method = RequestMethod.POST)
    public AjaxResult changeCard(Card card){
            AjaxResult ajaxResult=new AjaxResult();
        try {
            ajaxResult.setData(cardService.changeCard(card));
            ajaxResult.seterrcode(0);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
        } finally {
            return ajaxResult;
        }
    }
    @RequestMapping(value="/newCard",method = RequestMethod.POST)
    public AjaxResult newCard(Card card){
        AjaxResult ajaxResult=new AjaxResult();
        card.setCardStartDate(DateUtil.LocalDateToDate(LocalDate.now()));
        card.setCardEndDate(DateUtil.LocalDateToDate(LocalDate.now().plusDays(7)));
        try {


            ajaxResult.setData(cardService.newCard(card));
            ajaxResult.seterrcode(0);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
        } finally {
            return  ajaxResult;
        }
    }
    @RequestMapping(value = "/delCard",method = RequestMethod.POST)
    public AjaxResult delCard(@RequestBody Card card){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            ajaxResult.setData(cardService.delCard(card));
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
        } finally {
            return ajaxResult;
        }
    }
    @RequestMapping(value = "/updateCardList",method = RequestMethod.POST)
    public AjaxResult updateCardList(@RequestBody List<com.team6.leangoo.model.List> lists){
       return new AjaxResult(cardService.updateCardList(lists));
    }
}
