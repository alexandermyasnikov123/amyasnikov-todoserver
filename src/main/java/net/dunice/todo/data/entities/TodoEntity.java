package net.dunice.todo.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.With;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@With
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "todo")
public class TodoEntity {
    public static final String DATE_TIME_PATTERN = "yyyy.MM.dd, hh:mm:ss";

    public static final int DETAILS_MIN_LENGTH = 3;

    public static final int DETAILS_MAX_LENGTH = 160;

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
    Date creationDate;

    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    @JsonProperty(value = "updatedAt")
    Date lastUpdateDate;

    @JsonProperty(value = "status")
    @NonNull
    Boolean isReady;

    @PrePersist
    public void recordCreatedTime() {
        val now = new Date();
        setCreationDate(now);
        setLastUpdateDate(now);
    }

    @PreUpdate
    public void recordUpdateTime() {
        setLastUpdateDate(new Date());
    }
}

