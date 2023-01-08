package net.huray.project.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.huray.project.common.code.ExceptionCode;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class RestApiExceptionHandler {

    private final MessageSource message;

    /**
     * 커스텀 Exception
     */
    @ExceptionHandler(StatusException.class)
    public ResponseEntity<ApiError> handleResponseStatusException(final StatusException ex, HttpServletRequest request) {

        ExceptionCode code = ex.getCode();
        HttpStatus status = code.getStatus();

        return new ResponseEntity<>(ApiError.of(code, ex.getEtc(), status, request.getRequestURI()), status);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, NullPointerException.class})
    public ResponseEntity<ApiError> handleIllegalArgument(final IllegalArgumentException ex, HttpServletRequest request) {
        log.debug(ex.getMessage());

        String message = ex.getCause() != null ? ex.getCause().getLocalizedMessage() : ex.getMessage();
        return new ResponseEntity<>(ApiError.of(message, request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpServerErrorException.class} )
    public ResponseEntity<ApiError> handleCommonException(final HttpServerErrorException ex, HttpServletRequest request) {

        String message = ex.getCause() != null ? ex.getCause().getLocalizedMessage() : ex.getMessage();
        return new ResponseEntity<>(ApiError.of(message, request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
