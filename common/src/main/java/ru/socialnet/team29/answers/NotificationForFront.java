package ru.socialnet.team29.answers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.socialnet.team29.model.Person;
import ru.socialnet.team29.model.enums.NotificationType;

import java.time.LocalDateTime;
@Data
public class NotificationForFront
{
    private Integer id;
    @JsonProperty(value = "author")
    private Person person;
    private String content;
    private String notificationType;
    private LocalDateTime sentTime;

}
