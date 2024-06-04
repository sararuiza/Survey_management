package Riwi.Survey_management.api.error_handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import Riwi.Survey_management.api.dto.errors.BaseErrorResponse;
import Riwi.Survey_management.api.dto.errors.ErrorsResp;
import Riwi.Survey_management.utils.BadRequestException;


@RestControllerAdvice
public class BadRequestController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public BaseErrorResponse handleBadRequest(
                        MethodArgumentNotValidException exception) {

                List<String> errors = new ArrayList<>();

                exception.getAllErrors()
                                .forEach(error -> errors.add(error.getDefaultMessage()));

                return ErrorsResp.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .status(HttpStatus.BAD_REQUEST.name())
                                .errors(errors)
                                .build();
        }

        @ExceptionHandler(BadRequestException.class)
        public BaseErrorResponse badRequest(BadRequestException exception) {
                List<String> errors = new ArrayList<>();

                errors.add(exception.getMessage());

                return ErrorsResp.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .status(HttpStatus.BAD_REQUEST.name())
                                .errors(errors)
                                .build();
        }
    
}
