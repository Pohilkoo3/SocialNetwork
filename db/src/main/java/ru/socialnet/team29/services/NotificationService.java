package ru.socialnet.team29.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.socialnet.team29.answers.AddNewNotification;
import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.interfaceDb.NotificationInterface;
import ru.socialnet.team29.payloads.AddNotificationPayload;
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
    public List<NotificationForFront> getAllNotificationsById(Integer idPerson) {
        return notificationRepository.getAllNotificationsByPersonsId(idPerson);
    }

    @Override
    public AddNewNotification saveNewNotification(AddNotificationPayload payload) {
        return notificationRepository.addNewNotification(payload);
    }

}
