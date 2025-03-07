package com.server.vet.exception;

import com.server.vet.exception.model.ApiError;
import com.server.vet.exception.model.ResponseEntityBuilder;
import jakarta.persistence.RollbackException;
import lombok.NonNull;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;




@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<String>();


        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        details.add(builder.toString());

        ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Invalid JSON" ,details);

        return ResponseEntityBuilder.build(err);

    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Malformed JSON request" ,details);

        return ResponseEntityBuilder.build(err);
    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        List<String> details = new ArrayList<String>();
//        details = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(error -> error.getObjectName()+ " : " +error.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        ApiError err = new ApiError(LocalDateTime.now(),
//                HttpStatus.BAD_REQUEST,
//                "Validation Errors" ,
//                details);
//
//        return ResponseEntityBuilder.build(err);
//    }

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getParameterName() + " parameter is missing");

        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Missing Parameters" ,details);

        return ResponseEntityBuilder.build(err);
    }

    // handleMethodArgumentTypeMismatch : triggers when a parameter's type does not match
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Mismatch Type" ,details);

        return ResponseEntityBuilder.build(err);
    }

    // handleConstraintViolationException : triggers when @Validated fails
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Constraint Violation" ,details);

        return ResponseEntityBuilder.build(err);
    }
    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<Object> handleConstraintViolation(TransactionSystemException ex, WebRequest request) {

        List<String> details = new ArrayList<String>();

        if (ex.getCause() instanceof RollbackException) {
            RollbackException rollbackException = (RollbackException) ex.getCause();
            if (rollbackException.getCause() instanceof ConstraintViolationException) {
                details.add(rollbackException.getMessage());
                ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Constraint Violation" ,details);
                return ResponseEntityBuilder.build(err);
            }
        }

        details.add(ex.getMessage());
        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Constraint Violation" ,details);

        return ResponseEntityBuilder.build(err);
    }

    // handleResourceNotFoundException : triggers when there is not resource with the specified ID in BDD
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
//
//        List<String> details = new ArrayList<String>();
//        details.add(ex.getMessage());
//
//        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.NOT_FOUND, "Resource Not Found" ,details);
//
//        return ResponseEntityBuilder.build(err);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // handleNoHandlerFoundException : triggers when the handler method is invalid
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Method Not Found" ,details);

        return ResponseEntityBuilder.build(err);

    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getLocalizedMessage());

        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Error occurred" ,details);

        return ResponseEntityBuilder.build(err);

    }


    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Error occurred" ,details);
        return ResponseEntityBuilder.build(err);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


}