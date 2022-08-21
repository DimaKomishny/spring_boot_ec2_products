package com.dmytro.komyshnyi.ec2.controller;

import com.dmytro.komyshnyi.ec2.config.ExceptionHandlerController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionHandlerControllerTest {

    private static ExceptionHandlerController handlerController;

    @BeforeAll
    static void setup() {
        handlerController = new ExceptionHandlerController();
    }

    @Test
    public void shouldReturnCorrectMessageForException() {
        //GIVEN
        ResponseEntity<Map<String, String>> expectedResponseEntity =
                new ResponseEntity<>(Map.of("message", "Unexpected error"), HttpStatus.INTERNAL_SERVER_ERROR);
        Exception ex = new Exception("some error message");
        //WHEN
        ResponseEntity<Map<String, String>> actualResponseEntity = handlerController.handleException(ex);
        //THEN
        assertEquals(expectedResponseEntity, actualResponseEntity);
    }

    @Test
    public void shouldReturnCorrectMessageForEntityNotFoundException() {
        //GIVEN
        String message = "not found message";
        ResponseEntity<Map<String, String>> expectedResponseEntity =
                new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
        EntityNotFoundException ex = new EntityNotFoundException(message);
        //WHEN
        ResponseEntity<Map<String, String>> actualResponseEntity = handlerController.handleEntityNotFountException(ex);
        //THEN
        assertEquals(expectedResponseEntity, actualResponseEntity);
    }
}