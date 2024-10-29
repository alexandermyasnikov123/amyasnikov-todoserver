package net.dunice.todo.DTOs.responses;

import net.dunice.todo.DTOs.responses.common.CustomSuccessResponse;
import net.dunice.todo.constants.ErrorCodes;

public final class GetPaginatedResponse extends CustomSuccessResponse<TodoEntityPageResponse> {

    public GetPaginatedResponse(ErrorCodes errorCodes, TodoEntityPageResponse page) {
        super(errorCodes, page);
    }
}
