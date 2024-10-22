package net.dunice.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    UNKNOWN(0, "unknown"),

    MUST_NOT_BE_NULL(4, "must not be null"),

    PARAM_PAGE_NOT_NULL(15, "Required Integer parameter 'page' is not present"),

    PARAM_PER_PAGE_NOT_NULL(16, "Required Integer parameter 'perPage' is not present"),

    PER_PAGE_MIN_NOT_VALID(19, "perPage must be greater or equal 1"),

    PER_PAGE_MAX_NOT_VALID(19, "perPage must be less or equal 100"),

    EXCEPTION_HANDLER_NOT_PROVIDED(21, "Exception handler not provided"),

    ID_MUST_BE_POSITIVE(29, ValidationConstants.ID_MUST_BE_POSITIVE),

    TODO_TEXT_NOT_NULL(31, ValidationConstants.TODO_TEXT_NOT_NULL),

    TODO_TEXT_SIZE_NOT_VALID(32, ValidationConstants.TODO_TEXT_SIZE_NOT_VALID),

    TODO_STATUS_NOT_NULL(33, ValidationConstants.TODO_STATUS_NOT_NULL),

    REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT(40, ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT),

    REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT(41, ValidationConstants.REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT),

    private final int code;

    private final String message;
}
