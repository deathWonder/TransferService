package com.example.transferservicebest;

import com.example.transferservicebest.controller.TransferControllerImpl;
import com.example.transferservicebest.model.Result;
import com.example.transferservicebest.service.TransferServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestTransferController {

    @Mock
    TransferServiceImpl service;
    @InjectMocks
    TransferControllerImpl controller;

    @Test
    public void transfer_ReturnsValidResponseEntity() {
        //given
        Result result = new Result("1");
        Mockito.when(service.transferPrepare(Mockito.any())).thenReturn(result);
        //when
        ResponseEntity<Result> responseEntity = controller.transfer(Mockito.any());
        //then
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(result, responseEntity.getBody());
    }

    @Test
    public void confirm_ReturnsValidResponseEntity() {
        //given
        Result result = new Result("1");
        Mockito.when(service.transfer(Mockito.any())).thenReturn(result);
        //when
        ResponseEntity<Result> responseEntity = controller.confirm(Mockito.any());
        //then
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(result, responseEntity.getBody());
    }


}
