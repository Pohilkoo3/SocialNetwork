package ru.socialnet.team29.repositories;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.socialnet.team29.answers.AddNewNotification;
import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.domain.tables.Notification;
import ru.socialnet.team29.model.Person;
import ru.socialnet.team29.model.enums.NotificationType;
import ru.socialnet.team29.payloads.AddNotificationPayload;
import ru.socialnet.team29.services.DslContextCustom;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepository {

    private final DslContextCustom dslContextCustom;
    private static DSLContext dsl;
    private final PersonRepository personRepository;

    private void initDsl() {
        if (dsl == null) {
            dsl = dslContextCustom.initDslContext();
        }
    }

    public Integer getCountNotificationByPersonId(Integer id) {
        initDsl();
        return dsl.selectFrom(Notification.NOTIFICATION)
                .where(Notification.NOTIFICATION.PERSON_ID.eq(id))
                .fetch()
                .size();

    }


    public List<NotificationForFront> getAllNotificationsByPersonsId(Integer idPerson) {
        initDsl();
        List<ru.socialnet.team29.model.Notification> notificationList = dsl.selectFrom(Notification.NOTIFICATION)
                .where(Notification.NOTIFICATION.PERSON_ID.eq(idPerson))
                .fetch()
                .into(ru.socialnet.team29.model.Notification.class);
        List<NotificationForFront> result = new ArrayList<>();
        for (ru.socialnet.team29.model.Notification notification : notificationList) {
            NotificationForFront forFront = new NotificationForFront();
            forFront.setId(notification.getId());
            Person person = personRepository.findOnePersonModelByEmail(notification.getContact());
            forFront.setPerson(person);
            forFront.setContent("");
            forFront.setNotificationType(NotificationType.getTypeEnum(notification.getTypeId()));
            forFront.setSentTime(notification.getSentTime());
            result.add(forFront);
        }
        return result;
    }

    public AddNewNotification addNewNotification(AddNotificationPayload payload) throws NullPointerException {
        initDsl();
        ru.socialnet.team29.model.Notification notification = getNotificationFromPayload(payload);
        ru.socialnet.team29.model.Notification notificationSaved = dsl.insertInto(Notification.NOTIFICATION)
                .set(dsl.newRecord(Notification.NOTIFICATION, notification))
                .returning()
                .fetchOne()
                .into(ru.socialnet.team29.model.Notification.class);
        return createAddNewNotification(notificationSaved, payload);
    }

    private ru.socialnet.team29.model.Notification getNotificationFromPayload(AddNotificationPayload payload) {
        ru.socialnet.team29.model.Notification notification = new ru.socialnet.team29.model.Notification();
        notification.setTypeId(payload.getNotificationTypeId());
        notification.setSentTime(LocalDateTime.now());
        notification.setPersonId(Integer.parseInt(payload.getUserId()));
        notification.setContact(personRepository.findEmailByPersonId(Integer.parseInt(payload.getAuthorId())));
        return notification;
    }

    private AddNewNotification createAddNewNotification(ru.socialnet.team29.model.Notification notificationSaved, AddNotificationPayload payload) {
        AddNewNotification answer = new AddNewNotification();
        answer.setId(String.valueOf(notificationSaved.getId()));
        answer.setUserId(payload.getUserId());
        answer.setTime(LocalDateTime.now());
        answer.setAuthorId(payload.getAuthorId());
        answer.setNotificationType(payload.getNotificationType());
        answer.setIsStatusSent(true);
        return answer;
    }


    public ru.socialnet.team29.model.Notification addNewNotificationTest(ru.socialnet.team29.model.Notification notification) {
        initDsl();
        return dsl.insertInto(Notification.NOTIFICATION)
                .set(dsl.newRecord(Notification.NOTIFICATION, notification))
                .returning()
                .fetchOne()
                .into(ru.socialnet.team29.model.Notification.class);
    }

}

