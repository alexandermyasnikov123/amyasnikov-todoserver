package net.dunice.todo.models.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NonFinal
public class BaseSuccessResponse {
    int statusCode;

    boolean success;
}
