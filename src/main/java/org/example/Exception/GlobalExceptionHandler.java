package org.example.Exception;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.example.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDTO> handleCustomException(CustomException c, HttpServletRequest request){
        System.out.println("inside handleCustomException");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDTO(
                        LocalDateTime.now(),
                        c.getMessage(),
                        request.getRequestURI()
                ));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException c){
        return ResponseEntity.status(500).body(c.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(RuntimeException c){
        return ResponseEntity.status(500).body(c.getMessage());
    }
}
