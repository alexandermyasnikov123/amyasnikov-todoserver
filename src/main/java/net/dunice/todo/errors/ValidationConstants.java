package net.dunice.todo.errors;

import lombok.experimental.UtilityClass;
import net.dunice.todo.models.TodoEntity;

@UtilityClass
public class ValidationConstants {
    public static final String UNKNOWN =
            "unknown";

    public static final String MUST_NOT_BE_NULL =
            "must not be null";

    public static final String PARAMETER_PAGE_NOT_PRESENT;

    public static final String PARAMETER_PER_PAGE_NOT_PRESENT;

    public static final String PER_PAGE_MUST_BE_AT_LEAST_1;

    public static final String PER_PAGE_MUST_BE_LESS_100;

    public static final String ID_MUST_BE_POSITIVE =
            "An identifier must be a positive value.";

    public static final String TODO_TEXT_NOT_NULL =
            "The text of the TodoEntity task must be a non-null value.";

    public static final String TODO_TEXT_SIZE_NOT_VALID = """
            The text size is not valid. \
            The length must be in [%d, %d] range.""".formatted(TodoEntity.DETAILS_MIN_LENGTH, TodoEntity.DETAILS_MAX_LENGTH);

    public static final String TODO_STATUS_NOT_NULL =
            "The status of the TodoEntity task can't be a null value.";

    public static final String REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT;

    public static final String REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT;

    private static final String BASIC_INT_PARAM_MESSAGE = """
            Can't find a required int param '%s'. Do you add it?""";

    private static final String PARAMETER_NOT_PRESENT =
            "Required Integer parameter '%s' is not present";

    private static final String PER_PAGE_MUST_BE =
            "perPage must be %s or equal %d";

    static {
        REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT = BASIC_INT_PARAM_MESSAGE.formatted("per page");
        REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT = BASIC_INT_PARAM_MESSAGE.formatted("page");
        PER_PAGE_MUST_BE_LESS_100 = PER_PAGE_MUST_BE.formatted("less", 100);
        PER_PAGE_MUST_BE_AT_LEAST_1 = PER_PAGE_MUST_BE.formatted("greater", 1);
        PARAMETER_PAGE_NOT_PRESENT = PARAMETER_NOT_PRESENT.formatted("page");
        PARAMETER_PER_PAGE_NOT_PRESENT = PARAMETER_PAGE_NOT_PRESENT.formatted("perPage");
    }
}
