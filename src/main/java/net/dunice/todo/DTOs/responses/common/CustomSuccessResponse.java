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

    public CustomSuccessResponse(T data) {
        super(ErrorCodes.OK.getCode());
        this.data = data;
    }
}
