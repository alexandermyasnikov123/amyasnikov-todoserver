package net.dunice.todo.DTOs.responses.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import net.dunice.todo.constants.ErrorCodes;

@Value
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NonFinal
public class BaseSuccessResponse {
    int statusCode;

    boolean success;

    public BaseSuccessResponse(int statusCode) {
        this(statusCode, statusCode == ErrorCodes.OK.getCode());
    }

    public BaseSuccessResponse(ErrorCodes errorCodes) {
        this(errorCodes.getCode());
    }

    public static BaseSuccessResponse success() {
        return new BaseSuccessResponse(ErrorCodes.OK);
    }
}
