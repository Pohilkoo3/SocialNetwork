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
import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.model.Notification;
import ru.socialnet.team29.service.PersonServiceImpl;
import ru.socialnet.team29.serviceInterface.NotificationService;

import java.time.LocalDateTime;
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

        List<NotificationForFront> listNotifications = notificationService.getAllNotificationsForPerson(id);

        return  new ResponseEntity<>(new AnswerListAllNotificationsForPerson(LocalDateTime.now(),listNotifications), HttpStatus.OK) ;
//        return "{\"timeStamp\":\"2022-11-30T15:27:31.309Z\",\"data\":[{\"id\":0,\"author\":{\"id\":0,\"email\":\"string\",\"phone\":\"string\",\"photo\":\"string\",\"about\":\"string\",\"city\":\"string\",\"country\":\"string\",\"token\":\"string\",\"statusCode\":\"FRIEND\",\"firstName\":\"string\",\"lastName\":\"string\",\"regDate\":\"2022-11-30T15:27:31.309Z\",\"birthDate\":\"2022-11-30T15:27:31.309Z\",\"messagePermission\":\"string\",\"lastOnlineTime\":\"2022-11-30T15:27:31.309Z\",\"isOnline\":true,\"isBlocked\":true,\"isDeleted\":true,\"photoId\":\"string\",\"photoName\":\"string\",\"role\":\"USER\",\"createdOn\":\"2022-11-30T15:27:31.309Z\",\"updatedOn\":\"2022-11-30T15:27:31.309Z\",\"password\":\"string\"},\"content\":\"string\",\"notificationType\":\"FRIEND_REQUEST\",\"sentTime\":\"2022-11-30T15:27:31.309Z\"}]}";
    }
}

//"{//"timeStamp\":\"2022-11-30T15:27:31.309Z\",\"data\":[{\"id\":0,\"author\":{\"id\":0,\"email\":\"string\",\"phone\":\"string\",\"photo\":\"string\",\"about\":\"string\",\"city\":\"string\",\"country\":\"string\",\"token\":\"string\",\"statusCode\":\"FRIEND\",\"firstName\":\"string\",\"lastName\":\"string\",\"regDate\":\"2022-11-30T15:27:31.309Z\",\"birthDate\":\"2022-11-30T15:27:31.309Z\",\"messagePermission\":\"string\",\"lastOnlineTime\":\"2022-11-30T15:27:31.309Z\",\"isOnline\":true,\"isBlocked\":true,\"isDeleted\":true,\"photoId\":\"string\",\"photoName\":\"string\",\"role\":\"USER\",\"createdOn\":\"2022-11-30T15:27:31.309Z\",\"updatedOn\":\"2022-11-30T15:27:31.309Z\",\"password\":\"string\"},\"content\":\"string\",\"notificationType\":\"FRIEND_REQUEST\",\"sentTime\":\"2022-11-30T15:27:31.309Z\"}]}";
