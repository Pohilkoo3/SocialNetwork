package ru.socialnet.team29.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.model.Notification;
import ru.socialnet.team29.serviceInterface.NotificationService;
import ru.socialnet.team29.serviceInterface.feign.interfaces.DBConnectionFeignInterfaceNotification;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService
{
private final DBConnectionFeignInterfaceNotification feignInterfaceNotification;

    @Override
    public Integer getCountAllNotifications(int idPerson) {
        return feignInterfaceNotification.getCountNotification(idPerson);
    }

    @Override
    public List<NotificationForFront> getAllNotificationsForPerson(int id) {
        return feignInterfaceNotification.getAllNotificationsById(id);
    }
}
