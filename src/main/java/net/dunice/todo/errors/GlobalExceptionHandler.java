package net.dunice.todo.errors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import net.dunice.todo.DTOs.responses.common.BaseSuccessResponse;
import net.dunice.todo.DTOs.responses.common.ErrorSuccessResponse;
import net.dunice.todo.constants.ErrorCodes;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<BaseSuccessResponse> handleConstraintsExceptions(ConstraintViolationException exception) {
        List<String> messages = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        return mapToErrorResponse(messages);
    }

    @ExceptionHandler(value = BindException.class)
    protected ResponseEntity<BaseSuccessResponse> handleBindExceptions(BindException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return mapToErrorResponse(errors);
    }

    private ResponseEntity<BaseSuccessResponse> mapToErrorResponse(List<String> messages) {
        List<Integer> errors = messages.stream()
                .map(message -> errorCodes.getOrDefault(message, ErrorCodes.UNKNOWN).getCode())
                .toList();

        int errorCode = errors.stream().findFirst().orElseThrow();
        return ResponseEntity.badRequest()
                .body(ErrorSuccessResponse.withCurrentTimeStamp(errorCode, errors));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    protected ResponseEntity<BaseSuccessResponse> handleMissingParametersExceptions(
            MissingServletRequestParameterException exception
    ) {
        return mapToErrorResponse(List.of(exception.getMessage()));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<BaseSuccessResponse> handleEntityNotFoundError(EntityNotFoundException ignored) {
        return createBasicErrorResponse(ErrorCodes.TASK_NOT_FOUND);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ResponseEntity<BaseSuccessResponse> handleNotReadableError(HttpMessageNotReadableException ignored) {
        return createBasicErrorResponse(ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
    }

    private ResponseEntity<BaseSuccessResponse> createBasicErrorResponse(ErrorCodes errorCodes) {
        int required = errorCodes.getCode();
        return ResponseEntity.badRequest()
                .body(ErrorSuccessResponse.withCurrentTimeStamp(required, List.of(required)));
    }
}
