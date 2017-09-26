package com.team6.leangoo.model;

import javax.persistence.*;

@Table(name = "list_card")
public class ListCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "list_id")
    private Integer listId;


    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return card_id
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * @param cardId
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
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

}