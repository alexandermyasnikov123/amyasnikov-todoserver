package net.dunice.todo.errors;

import lombok.val;
import net.dunice.todo.constants.ErrorCodes;
import net.dunice.todo.constants.HeaderConstants;
import net.dunice.todo.dto.response.BaseSuccessResponse;
import net.dunice.todo.dto.response.CustomSuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final Map<String, ErrorCodes> errorCodes = Arrays.stream(ErrorCodes.values())
            .collect(Collectors.toMap(ErrorCodes::getMessage, errorCode -> errorCode));

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<BaseSuccessResponse> handleConflict(RuntimeException exception) {
        val errorCode = errorCodes.getOrDefault(exception.getMessage(), ErrorCodes.UNKNOWN);
        return ResponseEntity.badRequest()
                .header(HeaderConstants.ERROR_HEADER, errorCode.getMessage())
                .body(CustomSuccessResponse.from(errorCode, List.of(errorCode.getCode())));
    }
}
