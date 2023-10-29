package com.example.transferservicebest;

import com.example.transferservicebest.model.*;
import com.example.transferservicebest.repository.TransferRepositoryImpl;
import com.example.transferservicebest.service.TransferServiceImpl;
import com.example.transferservicebest.service.Validation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestTransferService {

    @Mock
    TransferRepositoryImpl repository;
    @Mock
    Validation validation;
    @InjectMocks
    TransferServiceImpl service;

    @Test
    public void Test_transfer() {
        //given
        Result expected = new Result("test_service_operation");
        ConfirmInfo request = new ConfirmInfo("1", "275173");
        Amount amount = new Amount(1000, "RUR");
        Operation operation = new Operation(
                "test_service_operation",
                "275173",
                "2234567890123456",
                "2234567890123457",
                amount
        );
        Amount cash = new Amount(10000, "RUR");
        Card card1 = new Card("2234567890123456", "11/26", "353", cash);
        Card card2 = new Card("2234567890123457", "11/26", "454", cash);

        Mockito.doNothing().when(validation).validConfirmInfo(Mockito.any());
        Mockito.when(repository.getOperation(Mockito.any())).thenReturn(operation);
        Mockito.when(repository.getCard(card1.getCardNumber())).thenReturn(card1);
        Mockito.when(repository.getCard(card2.getCardNumber())).thenReturn(card2);
        Mockito.doNothing().when(repository).amend(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(repository).putOperation(Mockito.any());

        //then
        Result result = service.transfer(request);
        //then
        Assert.assertEquals(expected, result);
    }
}
