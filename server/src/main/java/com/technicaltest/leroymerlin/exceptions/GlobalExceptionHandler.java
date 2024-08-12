package com.technicaltest.leroymerlin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Récupère tous les messages d'erreur de validation
        String errorMessage = ex.getBindingResult()
                                 .getAllErrors()
                                 .stream()
                                 .map(ObjectError::getDefaultMessage)
                                 .collect(Collectors.joining(", ")); // Joint tous les messages d'erreur en une seule chaîne, séparés par des virgules

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}



