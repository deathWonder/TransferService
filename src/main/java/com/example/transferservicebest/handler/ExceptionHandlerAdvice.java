package com.example.transferservicebest.handler;

import com.example.transferservicebest.exception.InputDataException;
import com.example.transferservicebest.exception.ServiceException;
import com.example.transferservicebest.model.ErrorResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.atomic.AtomicInteger;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    AtomicInteger generateId = new AtomicInteger(1);

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ErrorResult> invalidParameter(InputDataException e) {
        return ResponseEntity.badRequest().body(new ErrorResult(generateId.getAndIncrement(), e.getMessage()));
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResult> serviceError() {
        return ResponseEntity.internalServerError().body(new ErrorResult(generateId.getAndIncrement(), "Ошибка сервиса!"));
    }

}
