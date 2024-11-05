package net.dunice.todo.errors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import net.dunice.todo.DTOs.responses.common.BaseSuccessResponse;
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
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Map<String, ErrorCodes> errorCodes = Arrays.stream(ErrorCodes.values())
            .collect(Collectors.toMap(ErrorCodes::getMessage, errorCode -> errorCode));

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<BaseSuccessResponse> handleConstraintsExceptions(ConstraintViolationException exception) {
        Stream<String> messages = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage);

        return mapToErrorResponse(messages);
    }

    @ExceptionHandler(value = BindException.class)
    protected ResponseEntity<BaseSuccessResponse> handleBindExceptions(BindException exception) {
        Stream<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage);

        return mapToErrorResponse(errors);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    protected ResponseEntity<BaseSuccessResponse> handleMissingParametersExceptions(
            MissingServletRequestParameterException exception
    ) {
        return mapToErrorResponse(Stream.of(exception.getMessage()));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<BaseSuccessResponse> handleEntityNotFoundError(EntityNotFoundException exception) {
        return createBasicErrorResponse(List.of(exception.getErrorCode().getCode()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ResponseEntity<BaseSuccessResponse> handleNotReadableError(HttpMessageNotReadableException ignored) {
        return createBasicErrorResponse(List.of(ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getCode()));
    }

    private ResponseEntity<BaseSuccessResponse> createBasicErrorResponse(List<Integer> statuses) {
        Integer code = statuses.stream().findFirst().orElseThrow();

        return ResponseEntity.badRequest()
                .body(BaseSuccessResponse.failed(code, statuses));
    }

    private ResponseEntity<BaseSuccessResponse> mapToErrorResponse(Stream<String> messages) {
        List<Integer> errors = messages
                .map(message -> errorCodes.getOrDefault(message, ErrorCodes.UNKNOWN).getCode())
                .toList();

        return createBasicErrorResponse(errors);
    }
}
