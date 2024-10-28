package net.dunice.todo.constants;

import net.dunice.todo.data.entities.TodoEntity;

public interface ValidationConstants {
    String UNKNOWN = "unknown";

    String MUST_NOT_BE_NULL = "must not be null";

    String PARAMETER_PAGE_NOT_PRESENT =
            UnformattedConstants.PARAMETER_NOT_PRESENT.formatted("page");

    String PARAMETER_PER_PAGE_NOT_PRESENT =
            PARAMETER_PAGE_NOT_PRESENT.formatted("perPage");

    String PER_PAGE_MUST_BE_AT_LEAST_1 =
            UnformattedConstants.PER_PAGE_MUST_BE.formatted("greater", 1);

    String PER_PAGE_MUST_BE_LESS_100 =
            UnformattedConstants.PER_PAGE_MUST_BE.formatted("less", 100);

    String ID_MUST_BE_POSITIVE =
            "An identifier must be a positive value.";

    String TODO_TEXT_NOT_NULL =
            "The text of the TodoEntity task must be a non-null value.";

    String TODO_TEXT_SIZE_NOT_VALID = """
            The text size is not valid. \
            The length must be in [%d, %d] range."""
            .formatted(TodoEntity.DETAILS_MIN_LENGTH, TodoEntity.DETAILS_MAX_LENGTH);

    String TODO_STATUS_NOT_NULL =
            "The status of the TodoEntity task can't be a null value.";

    String REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT =
            UnformattedConstants.BASIC_INT_PARAM_MESSAGE.formatted("page");

    String REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT =
            UnformattedConstants.BASIC_INT_PARAM_MESSAGE.formatted("per page");
}
