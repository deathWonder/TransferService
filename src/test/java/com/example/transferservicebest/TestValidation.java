package com.example.transferservicebest;

import com.example.transferservicebest.exception.InputDataException;
import com.example.transferservicebest.model.Amount;
import com.example.transferservicebest.model.ConfirmInfo;
import com.example.transferservicebest.model.TransferInfo;
import com.example.transferservicebest.repository.TransferRepositoryImpl;
import com.example.transferservicebest.service.Validation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(MockitoJUnitRunner.class)
public class TestValidation {

    @Mock
    TransferRepositoryImpl repository;
    @InjectMocks
    Validation validation;

    @Test
    public void Test_validTransferInfo_Works() {
        //given
        Amount amount = new Amount(1000, "RUR");
        TransferInfo request = new TransferInfo("3456345634563456", "11/26", "353", "4356435643564356", amount);
        Mockito.when(repository.checkCards(Mockito.any())).thenReturn(true);
        //when then
        assertDoesNotThrow(() -> validation.validTransferInfo(request));
    }

    //then
    @Test(expected = InputDataException.class)
    public void Test_validTransferInfo_Exception() {
        //given
        Amount amount = new Amount(1000, "RUR");
        TransferInfo request = new TransferInfo("3456345634563456", "11/26", "353", "4356435643564356", amount);
        Mockito.when(repository.checkCards(Mockito.any())).thenReturn(false);
        //then
        validation.validTransferInfo(request);

    }

    @Test
    public void Test_validConfirmInfo_Works() {
        //given
        ConfirmInfo request = new ConfirmInfo("1", "275173");
        Mockito.when(repository.checkOperation(Mockito.any())).thenReturn(true);
        //when then
        assertDoesNotThrow(() -> validation.validConfirmInfo(request));
    }

    //then
    @Test(expected = InputDataException.class)
    public void Test_validConfirmInfo_Exception() {
        //given
        ConfirmInfo request = new ConfirmInfo("1", "275173");
        Mockito.when(repository.checkOperation(Mockito.any())).thenReturn(false);
        //when
        validation.validConfirmInfo(request);
    }
}
