package net.dunice.todo.DTOs.responses.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import net.dunice.todo.constants.ErrorCodes;

import java.util.ArrayList;
import java.util.Collection;

@Value
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NonFinal
public class BaseSuccessResponse {
    int statusCode;

    boolean success;

    Collection<Integer> codes = new ArrayList<>();

    public static BaseSuccessResponse success() {
        return from(ErrorCodes.OK);
    }

    public static BaseSuccessResponse from(ErrorCodes errorCode) {
        int code = errorCode.getCode();
        return new BaseSuccessResponse(code, errorCode == ErrorCodes.OK);
    }
}
