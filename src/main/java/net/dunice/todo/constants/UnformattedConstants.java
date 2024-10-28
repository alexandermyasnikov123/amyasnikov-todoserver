package net.dunice.todo.constants;

interface UnformattedConstants {
    String BASIC_INT_PARAM_MESSAGE = """
            Can't find a required int param '%s'. Do you add it?""";

    String PARAMETER_NOT_PRESENT =
            "Required Integer parameter '%s' is not present";

    String PER_PAGE_MUST_BE =
            "perPage must be %s or equal %d";
}
