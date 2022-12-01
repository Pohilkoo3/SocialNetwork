package ru.socialnet.team29.repositories;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.domain.tables.Notification;
import ru.socialnet.team29.domain.tables.records.NotificationRecord;
import ru.socialnet.team29.domain.tables.records.PersonRecord;
import ru.socialnet.team29.model.Person;
import ru.socialnet.team29.model.enums.NotificationType;
import ru.socialnet.team29.services.DslContextCustom;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepository
{

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
        List<ru.socialnet.team29.model.Notification> notificationList =  dsl.selectFrom(Notification.NOTIFICATION)
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
            forFront.setNotificationType(NotificationType.getNotificationType(notification.getTypeId()));
            forFront.setSentTime(notification.getSentTime());
            result.add(forFront);
        }

        return result;
    }
}
