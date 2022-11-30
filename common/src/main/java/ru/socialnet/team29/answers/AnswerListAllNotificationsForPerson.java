package ru.socialnet.team29.answers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.socialnet.team29.model.Notification;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerListAllNotificationsForPerson {
    private Long timestamp;

    @JsonProperty(value = "data")
    private List<Notification> countNotifications;

}
