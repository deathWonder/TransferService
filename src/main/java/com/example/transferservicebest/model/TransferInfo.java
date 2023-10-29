package com.example.transferservicebest.model;


public record TransferInfo(String cardFromNumber, String cardFromValidTill, String cardFromCVC,
                           String cardToNumber, Amount amount) {
}
