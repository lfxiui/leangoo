package com.team6.leangoo.model;

import javax.persistence.*;
import java.util.ArrayList;

public class List {
    @Id
    @Column(name = "list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listId;

    @Column(name = "list_name")
    private String listName;

    @Column(name = "list_locate")
    private Integer listLocate;

    @Transient
    private java.util.List<Card> cardList=new ArrayList<>();

    public java.util.List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(java.util.List<Card> cardList) {
        this.cardList = cardList;
    }

    /**
     * @return list_id
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * @param listId
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * @return list_name
     */
    public String getListName() {
        return listName;
    }

    /**
     * @param listName
     */
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     * @return list_locate
     */
    public Integer getListLocate() {
        return listLocate;
    }

    /**
     * @param listLocate
     */
    public void setListLocate(Integer listLocate) {
        this.listLocate = listLocate;
    }
}