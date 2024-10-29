package net.dunice.todo.constants;

public interface ValidationConstants {
    String UNKNOWN = "unknown";

    String ALL_OK = "All ok";

    String MUST_NOT_BE_NULL = "must not be null";

    String PARAMETER_PAGE_NOT_PRESENT =
            "Required Integer parameter 'page' is not present";

    String PARAMETER_PER_PAGE_NOT_PRESENT =
            "Required Integer parameter 'perPage' is not present";

    String PER_PAGE_MUST_BE_AT_LEAST_1 =
            "perPage must be greater or equal 1";

    String PER_PAGE_MUST_BE_LESS_100 =
            "perPage must be less or equal 100";

    String ID_MUST_BE_POSITIVE =
            "An identifier must be a PositiveOrZero value.";

    String TODO_TEXT_NOT_NULL =
            "The text of the TodoEntity task must be a non-null value.";

    String TODO_TEXT_SIZE_NOT_VALID = """
            The text size is not valid. \
            The length must be in [1, 100] range.""";

    String TODO_STATUS_NOT_NULL =
            "The status of the TodoEntity task can't be a null value.";

    String REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT =
            "Can't find a required int param 'page'. Do you add it?";

    String REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT =
            "Can't find a required int param 'perPage'. Do you add it?";
}
