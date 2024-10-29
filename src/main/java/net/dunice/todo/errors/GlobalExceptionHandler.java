package net.dunice.todo.errors;

import net.dunice.todo.DTOs.responses.common.BaseSuccessResponse;
import net.dunice.todo.DTOs.responses.common.CustomSuccessResponse;
import net.dunice.todo.constants.ErrorCodes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseSuccessResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
        List<Integer> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .map(message -> errorCodes.getOrDefault(message, ErrorCodes.UNKNOWN).getCode())
                .toList();

        int errorCode = errors.stream().findFirst().orElseThrow();
        return ResponseEntity.badRequest()
                .body(new CustomSuccessResponse<>(errorCode, errors));
    }
}
