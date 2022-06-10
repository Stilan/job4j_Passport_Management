package ru.job4j.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException elementException) {
        return new ResponseEntity<String>("Passport not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<String>("Passport with the given number and series exists", HttpStatus.BAD_REQUEST);
    }
}
