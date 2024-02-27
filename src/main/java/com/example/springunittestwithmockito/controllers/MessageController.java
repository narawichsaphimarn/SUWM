package com.example.springunittestwithmockito.controllers;

import com.example.springunittestwithmockito.constants.ResponseConstant;
import com.example.springunittestwithmockito.entities.MessageEntity;
import com.example.springunittestwithmockito.models.controller.Response;
import com.example.springunittestwithmockito.models.message.MessageBodyRequest;
import com.example.springunittestwithmockito.models.message.MessageBodyResponse;
import com.example.springunittestwithmockito.services.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping(path = "/messages")
    public ResponseEntity<Response<List<MessageEntity>>> getAll() {
        try {
            return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_SUCCESS, HttpStatus.OK.value(), this.messageService.getAll(), null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_ERROR_GENERAL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, ex.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/message/{id}")
    public ResponseEntity<Response<MessageBodyResponse>> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_SUCCESS, HttpStatus.OK.value(), this.messageService.getById(id), null), HttpStatus.OK);
        } catch (Exception ex) {
            if (ex instanceof NoSuchElementException) {
                return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_ERROR_NOT_FOUND, HttpStatus.NOT_FOUND.value(), null, ex.getLocalizedMessage()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_ERROR_GENERAL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, ex.getLocalizedMessage()), HttpStatus.OK);
            }
        }
    }

    @PostMapping(path = "/message")
    public ResponseEntity<Response<MessageBodyResponse>> create(@RequestBody MessageBodyRequest messageBodyRequest) {
        try {
            return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_SUCCESS, HttpStatus.OK.value(), this.messageService.save(messageBodyRequest), null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_ERROR_GENERAL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, ex.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @PutMapping(path = "/message/{id}")
    public ResponseEntity<Response<MessageBodyResponse>> create(@RequestBody MessageBodyRequest messageBodyRequest, @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_SUCCESS, HttpStatus.OK.value(), this.messageService.update(id, messageBodyRequest), null), HttpStatus.OK);
        } catch (Exception ex) {
            if (ex instanceof NoSuchElementException) {
                return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_ERROR_NOT_FOUND, HttpStatus.NOT_FOUND.value(), null, ex.getLocalizedMessage()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_ERROR_GENERAL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, ex.getLocalizedMessage()), HttpStatus.OK);
            }
        }
    }

    @DeleteMapping(path = "/message/{id}")
    public ResponseEntity<Response<MessageBodyResponse>> create(@PathVariable("id") Long id) {
        try {
            this.messageService.delete(id);
            return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_SUCCESS, HttpStatus.OK.value(), null, null), HttpStatus.OK);
        } catch (Exception ex) {
            if (ex instanceof NoSuchElementException) {
                return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_ERROR_NOT_FOUND, HttpStatus.NOT_FOUND.value(), null, ex.getLocalizedMessage()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response<>(ResponseConstant.RESPONSE_CODE_ERROR_GENERAL, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, ex.getLocalizedMessage()), HttpStatus.OK);
            }
        }
    }
}
