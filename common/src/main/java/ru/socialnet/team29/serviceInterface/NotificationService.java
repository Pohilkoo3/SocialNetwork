package ru.socialnet.team29.serviceInterface;

import org.springframework.stereotype.Component;
import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.model.Notification;

import java.util.List;


public interface NotificationService
{
    Integer getCountAllNotifications(int idPerson);

    List<NotificationForFront> getAllNotificationsForPerson(int id);
}
