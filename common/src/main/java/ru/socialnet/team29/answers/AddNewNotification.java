package ru.socialnet.team29.answers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNewNotification
{
    private String id;
    private LocalDateTime time;
    private String authorId;
    private String userId;
    private String content;
    private String notificationType;
    private Boolean isStatusSent;
}
