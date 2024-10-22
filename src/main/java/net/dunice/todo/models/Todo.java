package net.dunice.todo.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.With;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@With
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Todo {
    public static final String DATE_TIME_PATTERN = "yyyy.MM.dd, hh:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Long id;

    @Length(min = 1, max = 255)
    @NonNull
    String details;

    @PastOrPresent
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    @NonNull
    Date creationDate;

    @FutureOrPresent
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    @NonNull
    Date lastUpdateDate;

    @NonNull
    Boolean isReady;
}
