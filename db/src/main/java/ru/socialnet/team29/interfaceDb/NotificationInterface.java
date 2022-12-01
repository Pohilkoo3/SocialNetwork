package ru.socialnet.team29.interfaceDb;

import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.domain.tables.records.NotificationRecord;
import ru.socialnet.team29.model.Notification;
import ru.socialnet.team29.model.Person;

import java.util.List;

public interface NotificationInterface {

    Integer getCountNotificationsById(Integer id);

    List<NotificationForFront> getAllNotificationsById(Integer idPerson);
}
