package com.example.transferservicebest.model;

public class Operation {
    private final String operationId;
    private final String code;
    private final String cardFrom;
    private final String cardTo;
    private final Amount value;
    private volatile boolean done = false;

    public Operation(String operationId, String code, String cardFrom, String cardTo, Amount value) {
        this.operationId = operationId;
        this.code = code;
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.value = value;
    }

    public String getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }

    public String getCardFrom() {
        return cardFrom;
    }

    public String getCardTo() {
        return cardTo;
    }

    public Amount getValue() {
        return value;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationId='" + operationId + '\'' +
                ", code=" + code + '}';
    }
}
