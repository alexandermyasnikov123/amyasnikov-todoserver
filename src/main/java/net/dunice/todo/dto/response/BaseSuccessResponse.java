package net.dunice.todo.dto.response;

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

    public static BaseSuccessResponse unknown() {
        return new BaseSuccessResponse(ErrorCodes.UNKNOWN.getCode(), true);
    }
}
