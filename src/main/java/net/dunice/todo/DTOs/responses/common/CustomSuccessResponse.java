package net.dunice.todo.DTOs.responses.common;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import net.dunice.todo.constants.ErrorCodes;

@Value
@NonFinal
@EqualsAndHashCode(callSuper = true)
public class CustomSuccessResponse<T> extends BaseSuccessResponse {
    T data;

    public CustomSuccessResponse(int statusCode, T data) {
        super(statusCode);
        this.data = data;
    }

    public CustomSuccessResponse(ErrorCodes errorCodes, T data) {
        this(errorCodes.getCode(), data);
    }
}
