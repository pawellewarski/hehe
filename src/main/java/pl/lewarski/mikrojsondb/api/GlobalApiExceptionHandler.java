package pl.lewarski.mikrojsondb.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import pl.lewarski.mikrojsondb.util.ApiErrorMessage;
import pl.lewarski.mikrojsondb.api.exception.EmptyParameterNameException;

@Slf4j
@ControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {

        log.error("Obsluga " + ex.getClass().getSimpleName());

        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof EmptyParameterNameException) {
            EmptyParameterNameException exception = (EmptyParameterNameException) ex;
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return buildError(ApiError.fromException(exception), headers, status);
        } else if (ex instanceof HttpMessageNotReadableException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return buildError(ApiError.fromErrorMessage(ApiErrorMessage.INVALID_REQUEST), headers, status);
        } else if (ex instanceof NoHandlerFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return buildError(ApiError.fromErrorMessage(ApiErrorMessage.INVALID_REQUEST), headers, status);
        } else {
            log.error(ex.getMessage(), ex);
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return buildError(ApiError.fromErrorMessage(ApiErrorMessage.INTERNAL_SERVER_ERROR), headers, status);
        }
    }

    private ResponseEntity<ApiError> buildError(ApiError body, HttpHeaders headers, HttpStatus status) {
        return new ResponseEntity<>(body, headers, status);
    }
}
