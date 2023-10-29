package com.example.transferservicebest.service;

import com.example.transferservicebest.model.ConfirmInfo;
import com.example.transferservicebest.model.Result;
import com.example.transferservicebest.model.TransferInfo;

public interface TransferService {

    Result transferPrepare(TransferInfo request);

    Result transfer(ConfirmInfo request);

    String generateCode();

    String getId();

}
