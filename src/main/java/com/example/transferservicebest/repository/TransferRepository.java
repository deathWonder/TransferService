package com.example.transferservicebest.repository;

import com.example.transferservicebest.model.Card;
import com.example.transferservicebest.model.ConfirmInfo;
import com.example.transferservicebest.model.Operation;
import com.example.transferservicebest.model.TransferInfo;


public interface TransferRepository {
    boolean checkCards(TransferInfo info);

    Card getCard(String cardNumber);

    void putOperation(Operation operation);

    void amend(Card cardFrom, Card cardTo);

    boolean checkOperation(ConfirmInfo info);

    Operation getOperation(String operationId);
}
