package net.dunice.todo.others;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import net.dunice.todo.constants.ValidationConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PositiveOrZeroId.PositiveOrZeroValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveOrZeroId {
    String message() default ValidationConstants.ID_MUST_BE_POSITIVE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class PositiveOrZeroValidator implements ConstraintValidator<PositiveOrZeroId, Number> {

        @Override
        public boolean isValid(Number value, ConstraintValidatorContext context) {
            return value != null && value.doubleValue() >= 0;
        }
    }

}
