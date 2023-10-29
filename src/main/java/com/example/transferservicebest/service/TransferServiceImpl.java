package com.example.transferservicebest.service;

import com.example.transferservicebest.exception.InputDataException;
import com.example.transferservicebest.model.*;
import com.example.transferservicebest.repository.TransferRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransferServiceImpl implements TransferService {
    private final AtomicLong generateId = new AtomicLong(1);
    private final TransferRepository repository;
    private final Validation validation;
    private final Logger log = Logger.getLogger(TransferServiceImpl.class);

    public TransferServiceImpl(TransferRepository repository, Validation validation) {
        this.repository = repository;
        this.validation = validation;
    }

    public Result transferPrepare(TransferInfo request) throws InputDataException {

        validation.validTransferInfo(request);

        Operation operation = new Operation(
                getId(),
                generateCode(),
                request.cardFromNumber(),
                request.cardToNumber(),
                request.amount());

        repository.putOperation(operation);

        log.info(operation);

        return new Result(operation.getOperationId());
    }

    public Result transfer(ConfirmInfo request) {

        validation.validConfirmInfo(request);

        Operation operation = repository.getOperation(request.operationId());

        Card cardFrom = repository.getCard(operation.getCardFrom());
        Card cardTo = repository.getCard(operation.getCardTo());

        Amount amountFrom = cardFrom.getCash();
        Amount amountTo = cardTo.getCash();
        Amount amount = operation.getValue();

        if ((amountFrom.getValue() < amount.getValue())) {
            throw new InputDataException("Недостаточного средств для операции!");
        }

        int commission = (int) (amount.getValue() * 0.01);
        int sum = amount.getValue();
        amountFrom.setValue(amountFrom.getValue() - sum);
        amountTo.setValue((int) ((amountTo.getValue() + amount.getValue()) * 0.99));

        cardFrom.setCash(amountFrom);
        cardTo.setCash(amountTo);

        repository.amend(cardFrom, cardTo);

        operation.setDone(true);
        repository.putOperation(operation);

        String messageForLog =
                "Код операции: " + operation.getOperationId() + "\n" +
                        "Карта отправителя: " + operation.getCardFrom() + "\n" +
                        "Карта получателя: " + operation.getCardTo() + "\n" +
                        "Операция перервода на сумму: " + sum + "\n" +
                        "Комиссия: " + commission + "\n" +
                        "Статус: Выполнена";

        log.info(messageForLog);

        return new Result(operation.getOperationId());
    }

    public String generateCode() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return String.valueOf(n);
    }

    public String getId() {
        return String.valueOf(generateId.getAndIncrement());
    }

}
