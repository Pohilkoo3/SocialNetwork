package ru.socialnet.team29.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.socialnet.team29.answers.AnswerCountNotificationsForPerson;
import ru.socialnet.team29.answers.AnswerListAllNotificationsForPerson;
import ru.socialnet.team29.answers.CountNotifications;
import ru.socialnet.team29.model.Notification;
import ru.socialnet.team29.service.PersonServiceImpl;
import ru.socialnet.team29.serviceInterface.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final PersonServiceImpl personService;


    @GetMapping(value = "/notifications/count")
    public ResponseEntity<AnswerCountNotificationsForPerson> getCountOfNotifications() {
        log.info("Получаем запрос от фронта на количество уведомлений для  " + SecurityContextHolder.getContext().getAuthentication().getName());
        int id = personService.getIdPersonFromSecurityContext();

        return new ResponseEntity<>(new AnswerCountNotificationsForPerson(System.currentTimeMillis(),
                new CountNotifications(notificationService.getCountAllNotifications(id))),
                HttpStatus.OK);
    }

    @GetMapping(value = "/notifications")
    public ResponseEntity<AnswerListAllNotificationsForPerson> getNotificationsByIdPerson() {
        log.info("Получаем запрос от фронта на вывод всех уведомлений для  " + SecurityContextHolder.getContext().getAuthentication().getName());
        int id = personService.getIdPersonFromSecurityContext();

        List<Notification> listNotifications = notificationService.getAllNotificationsForPerson(id);

        return new ResponseEntity<>(new AnswerListAllNotificationsForPerson(System.currentTimeMillis(),
                listNotifications),
                HttpStatus.OK);
    }


}
