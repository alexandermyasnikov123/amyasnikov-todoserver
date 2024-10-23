package net.dunice.todo.models.response;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.val;
import net.dunice.todo.errors.ErrorCodes;

@Value
@EqualsAndHashCode(callSuper = true)
public class CustomSuccessResponse<T> extends BaseSuccessResponse {
    T data;

    private CustomSuccessResponse(int statusCode, boolean success, T data) {
        super(statusCode, success);
        this.data = data;
    }

    public static <T> CustomSuccessResponse<T> from(ErrorCodes codes, T data) {
        val code = codes.getCode();
        return new CustomSuccessResponse<>(code, code == ErrorCodes.UNKNOWN.getCode(), data);
    }

    public static <T> CustomSuccessResponse<T> success(T data) {
        return new CustomSuccessResponse<>(ErrorCodes.UNKNOWN.getCode(), true, data);
    }
}
