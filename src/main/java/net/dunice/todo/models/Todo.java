package net.dunice.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@With
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {
    public static final String DATE_TIME_PATTERN = "yyyy.MM.dd, hh:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Long id;

    @Length(min = 1, max = 255)
    @JsonProperty(value = "text")
    @NonNull
    String details;

    @PastOrPresent
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    @JsonProperty(value = "createdAt")
    @NonNull
    Date creationDate;

    @FutureOrPresent
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    @JsonProperty(value = "updatedAt")
    @NonNull
    Date lastUpdateDate;

    @JsonProperty(value = "status")
    @NonNull
    Boolean isReady;
}

