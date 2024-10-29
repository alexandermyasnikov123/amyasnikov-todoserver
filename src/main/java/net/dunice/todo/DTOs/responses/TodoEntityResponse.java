package net.dunice.todo.DTOs.responses;

import net.dunice.todo.DTOs.responses.common.CustomSuccessResponse;
import net.dunice.todo.constants.ErrorCodes;
import net.dunice.todo.entities.TodoEntity;

public final class TodoEntityResponse extends CustomSuccessResponse<TodoEntity> {

    public TodoEntityResponse(ErrorCodes errorCodes, TodoEntity data) {
        super(errorCodes, data);
    }
}
