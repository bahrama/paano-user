package ir.tehranluster.paano.exceptions;

import ir.tehranluster.paano.dto.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
@RestController
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    ApiErrorResponse error = new ApiErrorResponse();
    @ExceptionHandler
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exc , WebRequest webRequest) {


        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(LocalDateTime.now());
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCustomException(CustomException exc , WebRequest webRequest) {
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(LocalDateTime.now());
        error.setStatusCode(555);
        return new ResponseEntity<>(error, error.getStatus());
    }

//    @ExceptionHandler(value = {CustomException.class , NullPointerException.class})
//    public ResponseEntity<Object> handleCustomException(CustomException exc , WebRequest webRequest) {
//
//        ApiErrorResponse error = new ApiErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST);
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(LocalDateTime.now());
//        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        return new ResponseEntity<>(error, error.getStatus());
//    }
}
