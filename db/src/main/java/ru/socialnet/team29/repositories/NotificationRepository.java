package ru.socialnet.team29.repositories;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.socialnet.team29.domain.tables.Notification;
import ru.socialnet.team29.domain.tables.Person;
import ru.socialnet.team29.domain.tables.records.NotificationRecord;
import ru.socialnet.team29.domain.tables.records.PersonRecord;
import ru.socialnet.team29.services.DslContextCustom;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepository
{

    private final DslContextCustom dslContextCustom;
    private static DSLContext dsl;

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


    public List<ru.socialnet.team29.model.Notification> getAllNotificationsByPersonsId(Integer idPerson) {
        initDsl();
        return dsl.selectFrom(Notification.NOTIFICATION)
                .where(Notification.NOTIFICATION.PERSON_ID.eq(idPerson))
                .fetch()
                .into(ru.socialnet.team29.model.Notification.class);



    }
}
