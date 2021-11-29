package com.mar.mardemo.controllers.api.v1;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


public class BaseController extends ResponseEntityExceptionHandler {
    
    /** 
     * @param e
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("handleConstraintViolationException "+ "Not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    
    /** 
     * @param e
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> exceptionHandlerIllegalArgumentException(final IllegalArgumentException e) {
        return new ResponseEntity<>("exceptionHandlerIllegalArgumentException"+ e.getMessage(), HttpStatus.BAD_REQUEST);
    }
 
    
    /** 
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    /*ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
        ex.getBindingResult().toString());*/
        return new ResponseEntity<>("handleMethodArgumentNotValid: "+ ex.getBindingResult(), HttpStatus.BAD_REQUEST);
    } 

    
    /** 
     * @param ex
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("'%s' should be a valid '%s' and '%s' isn't", name, type, value);

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    
    /** 
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity<Object>
     */
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String name = ex.getParameterName();
        logger.error(name + " parameter is missing");

        return new ResponseEntity<>("Missing parameter: "+ name, HttpStatus.BAD_REQUEST);
    }
}