package net.dunice.todo.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    UNKNOWN(0, ValidationConstants.UNKNOWN),

    MUST_NOT_BE_NULL(4, ValidationConstants.MUST_NOT_BE_NULL),

    PARAM_PAGE_NOT_NULL(15, ValidationConstants.PARAMETER_PAGE_NOT_PRESENT),

    PARAM_PER_PAGE_NOT_NULL(16, ValidationConstants.PARAMETER_PER_PAGE_NOT_PRESENT),

    PER_PAGE_MIN_NOT_VALID(19, ValidationConstants.PER_PAGE_MUST_BE_AT_LEAST_1),

    PER_PAGE_MAX_NOT_VALID(19, ValidationConstants.PER_PAGE_MUST_BE_LESS_100),

    ID_MUST_BE_POSITIVE(29, ValidationConstants.ID_MUST_BE_POSITIVE),

    TODO_TEXT_NOT_NULL(31, ValidationConstants.TODO_TEXT_NOT_NULL),

    TODO_TEXT_SIZE_NOT_VALID(32, ValidationConstants.TODO_TEXT_SIZE_NOT_VALID),

    TODO_STATUS_NOT_NULL(33, ValidationConstants.TODO_STATUS_NOT_NULL),

    REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT(40, ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT),

    REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT(41, ValidationConstants.REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT);

    private final int code;

    private final String message;
}
