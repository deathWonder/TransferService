package com.example.transferservicebest.service;

import com.example.transferservicebest.exception.InputDataException;
import com.example.transferservicebest.model.Amount;
import com.example.transferservicebest.model.ConfirmInfo;
import com.example.transferservicebest.model.TransferInfo;
import com.example.transferservicebest.repository.TransferRepository;
import io.micrometer.common.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validation {

    private final Pattern cardNumberPattern = Pattern.compile("([2-6]([0-9]{3}) ?)(([0-9]{4} ?){3})");
    private final Pattern datePattern = Pattern.compile("(0[1-9]|1[0-2])/([0-9]{2})");
    private final Pattern CVCPattern = Pattern.compile("[0-9]{3}");

    private final TransferRepository repository;

    public Validation(TransferRepository repository) {
        this.repository = repository;
    }

    public void validTransferInfo(TransferInfo info) {
        Matcher cardFromNumberMatcher = cardNumberPattern.matcher(info.cardFromNumber());
        Matcher cardToNumberMatcher = cardNumberPattern.matcher(info.cardToNumber());
        Matcher dataMatcher = datePattern.matcher(info.cardFromValidTill());
        Matcher CVCMatcher = CVCPattern.matcher(info.cardFromCVC());

        Amount amount = info.amount();
        int value = amount.getValue();
        String currency = amount.getCurrency();

        if (!cardFromNumberMatcher.matches()) {
            throw new InputDataException("Карта отправителя не соответствует паттерну!");
        }
        if (!cardToNumberMatcher.matches()) {
            throw new InputDataException("Карта получателя не соответствует паттерну!");
        }
        if (!dataMatcher.matches()) {
            throw new InputDataException("Срок действия карты не соответствует паттерну!");
        }
        if (!CVCMatcher.matches()) {
            throw new InputDataException("Код безопасности не соответствует паттерну!");
        }
        if (value <= 0) {
            throw new InputDataException("Указана неверная сумма операции!");
        }
        if (!StringUtils.isNotEmpty(currency)) {
            throw new InputDataException("Не указана валюта!");
        }
        if (!repository.checkCards(info)) {
            throw new InputDataException("Неверные данные!");
        }
    }

    public void validConfirmInfo(ConfirmInfo info) {

        String id = info.operationId();
        String code = info.code();

        if (!StringUtils.isNotEmpty(id)) {
            throw new InputDataException("Не указан номер операции!");
        }
        if (!StringUtils.isNotEmpty(code)) {
            throw new InputDataException("Не указан код подтверждения!");
        }
        if (!repository.checkOperation(info)) {
            throw new InputDataException("Операция не найдена!");
        }
    }

}
