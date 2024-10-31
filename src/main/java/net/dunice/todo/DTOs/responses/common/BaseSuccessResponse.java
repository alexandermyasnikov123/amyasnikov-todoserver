package net.dunice.todo.DTOs.responses.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import net.dunice.todo.constants.ErrorCodes;
import java.util.Date;
import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@NonFinal
public class BaseSuccessResponse {
    Integer statusCode;

    Boolean success;

    Iterable<Integer> codes;

    @Nullable
    Date timeStamp;

    protected BaseSuccessResponse(Integer statusCode) {
        this(statusCode, true, List.of(), null);
    }

    public static BaseSuccessResponse success() {
        return new BaseSuccessResponse(ErrorCodes.OK.getCode());
    }

    public static BaseSuccessResponse failed(Integer statusCode, Iterable<Integer> statusCodes) {
        return new BaseSuccessResponse(statusCode, true, statusCodes, new Date());
    }
}
