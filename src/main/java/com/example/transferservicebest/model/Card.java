package com.example.transferservicebest.model;

public class Card {
    private String cardNumber;
    private String cardValidTill;
    private String cardCVC;
    private Amount cash;

    public Card(String cardNumber, String cardValidTill, String cardCVC, Amount cash) {
        this.cardNumber = cardNumber;
        this.cardValidTill = cardValidTill;
        this.cardCVC = cardCVC;
        this.cash = cash;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardValidTill() {
        return cardValidTill;
    }

    public String getCardCVC() {
        return cardCVC;
    }

    public Amount getCash() {
        return cash;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardValidTill(String cardValidTill) {
        this.cardValidTill = cardValidTill;
    }

    public void setCardCVC(String cardCVC) {
        this.cardCVC = cardCVC;
    }

    public void setCash(Amount cash) {
        this.cash = cash;
    }
}
