package com.example.springunittestwithmockito.models.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    private String code;
    private int status;
    private T data;
    private String errorMsg;
}
