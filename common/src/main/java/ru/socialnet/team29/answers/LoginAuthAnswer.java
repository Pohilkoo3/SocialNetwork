package ru.socialnet.team29.answers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.socialnet.team29.model.Person;
import ru.socialnet.team29.model.enums.MessagePermission;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LoginAuthAnswer
{
    private String error;
    @JsonProperty(value = "timestamp")
    private Long requestTime;
    @JsonProperty(value = "data")
    private Person person;
    private String token;

}
