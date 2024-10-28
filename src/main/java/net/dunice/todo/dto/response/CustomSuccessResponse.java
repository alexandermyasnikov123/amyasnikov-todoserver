package net.dunice.todo.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.val;
import net.dunice.todo.constants.ErrorCodes;

@Value
@EqualsAndHashCode(callSuper = true)
public class CustomSuccessResponse<T> extends BaseSuccessResponse {
    T data;

    private CustomSuccessResponse(int statusCode, boolean success, T data) {
        super(statusCode, success);
        this.data = data;
    }

    public static <T> CustomSuccessResponse<T> success(T data) {
        return new CustomSuccessResponse<>(ErrorCodes.OK.getCode(), true, data);
    }

    public static <T> CustomSuccessResponse<T> from(ErrorCodes errorCode, T data) {
        val code = errorCode.getCode();
        return new CustomSuccessResponse<>(code, errorCode == ErrorCodes.OK, data);
    }
}
