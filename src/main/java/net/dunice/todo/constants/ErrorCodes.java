package net.dunice.todo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    UNKNOWN(0, ValidationConstants.UNKNOWN),

    OK(1, ValidationConstants.ALL_OK),

    MUST_NOT_BE_NULL(4, ValidationConstants.MUST_NOT_BE_NULL),

    PARAM_PAGE_NOT_NULL(15, ValidationConstants.PARAM_PAGE_NOT_NULL),

    PARAM_PER_PAGE_NOT_NULL(16, ValidationConstants.PARAM_PER_PAGE_NOT_NULL),

    ID_MUST_BE_POSITIVE(29, ValidationConstants.ID_MUST_BE_POSITIVE),

    TODO_TEXT_NOT_NULL(31, ValidationConstants.TODO_TEXT_NOT_NULL),

    TODO_TEXT_SIZE_NOT_VALID(32, ValidationConstants.TODO_TEXT_SIZE_NOT_VALID),

    TODO_STATUS_NOT_NULL(33, ValidationConstants.TODO_STATUS_NOT_NULL),

    TASK_NOT_FOUND(34, ValidationConstants.TASK_NOT_FOUND),

    TASKS_PAGE_GREATER_OR_EQUAL_1(37, ValidationConstants.PAGE_MUST_BE_AT_LEAST_1),

    TASKS_PER_PAGE_GREATER_OR_EQUAL_1(38, ValidationConstants.PER_PAGE_MUST_BE_AT_LEAST_1),

    TASKS_PER_PAGE_LESS_OR_EQUAL_100(39, ValidationConstants.PER_PAGE_MUST_BE_LESS_100),

    REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT(40, ValidationConstants.PARAMETER_PAGE_NOT_PRESENT),

    REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT(41, ValidationConstants.PARAMETER_PER_PAGE_NOT_PRESENT),

    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(47, ValidationConstants.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);

    private final int code;

    private final String message;
}
