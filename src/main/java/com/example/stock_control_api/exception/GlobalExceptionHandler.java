package com.example.stock_control_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MensagemErro> handleRuntime(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MensagemErro(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErro> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos.");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MensagemErro(msg));
    }
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<MensagemErro> handleDataIntegrityViolation(org.springframework.dao.DataIntegrityViolationException ex) {

        String message = "Violação de integridade dos dados.";

        if (ex.getMessage() != null && ex.getMessage().contains("usuarios_email_key")) {
            message = "Este e-mail já está cadastrado.";
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new MensagemErro(message));
    }

}
