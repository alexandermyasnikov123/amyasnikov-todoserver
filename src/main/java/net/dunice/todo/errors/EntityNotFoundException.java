package net.dunice.todo.errors;

import net.dunice.todo.constants.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends RuntimeException {
    ErrorCodes getErrorCode() {
        return ErrorCodes.TASK_NOT_FOUND;
    }
}
