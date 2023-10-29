package com.example.transferservicebest.repository;

import com.example.transferservicebest.exception.InputDataException;
import com.example.transferservicebest.model.*;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepositoryImpl implements TransferRepository {

    private final ConcurrentHashMap<String, Card> listCards = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Operation> operationQueue = new ConcurrentHashMap<>();

    public TransferRepositoryImpl() {}

    public boolean checkCards(TransferInfo info) { //проверяем карты, срок карты, код безопасности
        return listCards.containsKey(info.cardFromNumber()) &&
                listCards.containsKey(info.cardToNumber()) &&
                listCards.get(info.cardFromNumber()).getCardValidTill().equals(info.cardFromValidTill()) &&
                listCards.get(info.cardFromNumber()).getCardCVC().equals(info.cardFromCVC());
    }

    public boolean checkOperation(ConfirmInfo info) {

        if (operationQueue.containsKey(info.operationId())) {//Существует ли операция

            Operation operation = operationQueue.get(info.operationId());

            if (!operation.getCode().equals(info.code())) {//Проверяем код безопасности
                throw new InputDataException("Неверный код!");
            }
            if (operation.isDone()) {                      //Проверяем статус операции
                throw new InputDataException("Операция выполнена!");
            }
            return true;
        }
        return false;
    }

    public Card getCard(String cardNumber) {
        return listCards.get(cardNumber);
    }

    public void putOperation(Operation operation) {
        operationQueue.put(operation.getOperationId(), operation);
    }

    public Operation getOperation(String operationId) {
        return operationQueue.get(operationId);
    }

    public void amend(Card cardFrom, Card cardTo) {
        listCards.put(cardFrom.getCardNumber(), cardFrom);
        listCards.put(cardTo.getCardNumber(), cardTo);
    }

}
