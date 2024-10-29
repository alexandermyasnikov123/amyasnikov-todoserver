package net.dunice.todo.errors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import net.dunice.todo.DTOs.responses.common.BaseSuccessResponse;
import net.dunice.todo.DTOs.responses.common.CustomSuccessResponse;
import net.dunice.todo.constants.ErrorCodes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Map<String, ErrorCodes> errorCodes = Arrays.stream(ErrorCodes.values())
            .collect(Collectors.toMap(ErrorCodes::getMessage, errorCode -> errorCode));

    private List<String> findValidationErrorMessages(Exception exception) {
        if (exception instanceof ConstraintViolationException exc) {
            return exc.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
        } else if (exception instanceof BindException exc) {
            return exc.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
        }

        return List.of();
    }

    @ExceptionHandler(value = {BindException.class, ConstraintViolationException.class})
    protected ResponseEntity<BaseSuccessResponse> handleValidationErrors(Exception exception) {
        List<String> messages = findValidationErrorMessages(exception);

        List<Integer> errors = messages.stream()
                .map(message -> errorCodes.getOrDefault(message, ErrorCodes.UNKNOWN).getCode())
                .toList();

        int errorCode = errors.stream().findFirst().orElseThrow();
        return ResponseEntity.badRequest()
                .body(new CustomSuccessResponse<>(errorCode, errors));
    }
}
