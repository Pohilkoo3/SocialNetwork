package ru.socialnet.team29.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.socialnet.team29.domain.tables.records.NotificationRecord;
import ru.socialnet.team29.interfaceDb.NotificationInterface;
import ru.socialnet.team29.model.Notification;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationInterface notificationService;


    @GetMapping("/notifications/count")
    public int getCountNotifications(@RequestParam("idPerson") Integer idPerson){
        log.info("Получили запрос от core - Найти количество уведомлений пользователя => " + idPerson);
       int count = notificationService.getCountNotificationsById(idPerson);
        return count;
    }

    @GetMapping("/notifications")
    public List<Notification> getAllNotificationsByPersonId(@RequestParam("idPerson") Integer idPerson){
        log.info("Получили запрос от core - Найти все уведомления пользователя => " + idPerson);
        List<Notification> notificationsList = notificationService.getAllNotificationsById(idPerson);
        return notificationsList;
    }
}
