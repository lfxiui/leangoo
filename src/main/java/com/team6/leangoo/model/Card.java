package com.team6.leangoo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_intro")
    private String cardIntro;

    @Column(name = "card_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cardEndDate;

    @Column(name = "card_start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cardStartDate;

    @Column(name = "card_label")
    private String cardLabel;

    @Column(name = "card_workload")
    private Double cardWorkload;

    @Column(name = "card_locate")
    private Integer cardLocate;

    @Column(name = "card_list_id")
    private Integer cardListId;

    public Integer getCardListId() {
        return cardListId;
    }

    public void setCardListId(Integer cardListId) {
        this.cardListId = cardListId;
    }

    public Integer getCardLocate() {
        return cardLocate;
    }

    public void setCardLocate(Integer cardLocate) {
        this.cardLocate = cardLocate;
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
     * @return card_name
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return card_intro
     */
    public String getCardIntro() {
        return cardIntro;
    }

    /**
     * @param cardIntro
     */
    public void setCardIntro(String cardIntro) {
        this.cardIntro = cardIntro;
    }

    /**
     * @return card_end_date
     */
    public Date getCardEndDate() {
        return cardEndDate;
    }

    /**
     * @param cardEndDate
     */
    public void setCardEndDate(Date cardEndDate) {
        this.cardEndDate = cardEndDate;
    }

    /**
     * @return card_start_date
     */
    public Date getCardStartDate() {
        return cardStartDate;
    }

    /**
     * @param cardStartDate
     */
    public void setCardStartDate(Date cardStartDate) {
        this.cardStartDate = cardStartDate;
    }

    /**
     * @return card_label
     */
    public String getCardLabel() {
        return cardLabel;
    }

    /**
     * @param cardLabel
     */
    public void setCardLabel(String cardLabel) {
        this.cardLabel = cardLabel;
    }

    /**
     * @return card_workload
     */
    public Double getCardWorkload() {
        return cardWorkload;
    }

    /**
     * @param cardWorkload
     */
    public void setCardWorkload(Double cardWorkload) {
        this.cardWorkload = cardWorkload;
    }
}