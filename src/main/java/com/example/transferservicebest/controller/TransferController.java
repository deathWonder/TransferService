package com.example.transferservicebest.controller;

import com.example.transferservicebest.model.ConfirmInfo;
import com.example.transferservicebest.model.Result;
import com.example.transferservicebest.model.TransferInfo;
import org.springframework.http.ResponseEntity;

public interface TransferController {

    ResponseEntity<Result> transfer(TransferInfo request);

    ResponseEntity<Result> confirm(ConfirmInfo request);
}
