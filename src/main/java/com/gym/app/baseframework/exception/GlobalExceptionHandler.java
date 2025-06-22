package com.gym.app.baseframework.exception;

import com.gym.app.baseframework.exception.enums.ApiErrors;
import com.gym.app.service.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle your custom SystemException
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ApiErrorResponse> handleSystemException(SystemException ex, HttpServletRequest request) {
        logger.error("SystemException occurred: {}", ex.getErrorDescription(), ex);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                ApiErrors.LOOKUP_BY_ERROR_CODE.getOrDefault(ex.getErrorCode(), ApiErrors.INTERNAL_PROCESSING_ERROR),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }

    // Optional: handle generic exceptions to avoid leaking stack traces
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("Unhandled exception occurred: {}", ex.getMessage(), ex);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                ApiErrors.INTERNAL_PROCESSING_ERROR,
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
