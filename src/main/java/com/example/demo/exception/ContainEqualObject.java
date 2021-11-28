package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class ContainEqualObject extends RuntimeException {

    public ContainEqualObject() {
    }

    public ContainEqualObject(String message) {
        super(message);
    }
}
