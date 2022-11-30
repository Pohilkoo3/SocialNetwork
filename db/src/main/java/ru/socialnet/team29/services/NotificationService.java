package ru.socialnet.team29.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.socialnet.team29.domain.tables.records.NotificationRecord;
import ru.socialnet.team29.interfaceDb.NotificationInterface;
import ru.socialnet.team29.model.Notification;
import ru.socialnet.team29.repositories.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationInterface{
    private final NotificationRepository notificationRepository;



    public Integer getCountNotificationsById(Integer id) {
       return notificationRepository.getCountNotificationByPersonId(id);

    }

    @Override
    public List<Notification> getAllNotificationsById(Integer idPerson) {
        return notificationRepository.getAllNotificationsByPersonsId(idPerson);
    }
}
