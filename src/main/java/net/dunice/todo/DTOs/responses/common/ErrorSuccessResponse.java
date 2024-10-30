package net.dunice.todo.DTOs.responses.common;

import lombok.EqualsAndHashCode;
import lombok.Value;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Value
public class ErrorSuccessResponse extends BaseSuccessResponse {
    Date timeStamp;

    Iterable<Integer> codes;

    public ErrorSuccessResponse(int statusCode, Date timeStamp, Iterable<Integer> codes) {
        super(statusCode);
        this.timeStamp = timeStamp;
        this.codes = codes;
    }

    public static ErrorSuccessResponse withCurrentTimeStamp(int statusCode, Iterable<Integer> codes) {
        return new ErrorSuccessResponse(statusCode, new Date(), codes);
    }
}
