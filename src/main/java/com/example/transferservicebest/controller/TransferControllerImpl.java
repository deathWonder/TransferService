package com.example.transferservicebest.controller;

import com.example.transferservicebest.model.ConfirmInfo;
import com.example.transferservicebest.model.Result;
import com.example.transferservicebest.model.TransferInfo;
import com.example.transferservicebest.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransferControllerImpl implements TransferController {
    private final TransferService service;

    public TransferControllerImpl(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Result> transfer(@RequestBody TransferInfo request) {
        return ResponseEntity.ok(service.transferPrepare(request));
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<Result> confirm(@RequestBody ConfirmInfo request) {
        return ResponseEntity.ok(service.transfer(request));
    }
}