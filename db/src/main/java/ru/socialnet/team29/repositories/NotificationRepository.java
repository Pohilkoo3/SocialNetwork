package ru.socialnet.team29.repositories;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.socialnet.team29.answers.AddNewNotification;
import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.domain.tables.Notificationcommon;
import ru.socialnet.team29.mappers.NotificationMapperClass;
import ru.socialnet.team29.model.NotificationCommon;
import ru.socialnet.team29.payloads.AddNotificationPayload;
import ru.socialnet.team29.services.DslContextCustom;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepository {

    private final DslContextCustom dslContextCustom;
    private static DSLContext dsl;

    private final NotificationMapperClass notificationMapperClass;

    private void initDsl() {
        if (dsl == null) {
            dsl = dslContextCustom.initDslContext();
        }
    }

    public Integer getCountNotificationByPersonId(Integer id) {
        initDsl();
        return dsl.selectFrom(Notificationcommon.NOTIFICATIONCOMMON)
                .where(Notificationcommon.NOTIFICATIONCOMMON.PERSON_ID.eq(id))
                .fetch()
                .size();
    }


    public List<NotificationCommon> getNotificationCommonTrain(Integer idPerson) {
        initDsl();
        List<NotificationCommon> notificationCommonList = dsl.selectFrom(Notificationcommon.NOTIFICATIONCOMMON)
                .where(Notificationcommon.NOTIFICATIONCOMMON.PERSON_ID.eq(idPerson))
                .fetch()
                .into(NotificationCommon.class);
        return notificationCommonList;
    }


    public List<NotificationForFront> getAllNotificationsByPersonsId(Integer idPerson) {
        initDsl();
        List<NotificationCommon> notificationCommonList = dsl.selectFrom(Notificationcommon.NOTIFICATIONCOMMON)
                .where(Notificationcommon.NOTIFICATIONCOMMON.PERSON_ID.eq(idPerson))
                .fetch()
                .into(NotificationCommon.class);
        return notificationMapperClass.listNotificationCommonToListNotificationForFront(notificationCommonList);
    }

    public AddNewNotification addNewNotification(AddNotificationPayload payload) throws NullPointerException {
        initDsl();
        NotificationCommon notificationCommon = notificationMapperClass.addNotificationPayloadToNotificationCommon(payload);
        NotificationCommon notificationCommonSaved = dsl.insertInto(Notificationcommon.NOTIFICATIONCOMMON)
                .set(dsl.newRecord(Notificationcommon.NOTIFICATIONCOMMON, notificationCommon))
                .returning()
                .fetchOne()
                .into(NotificationCommon.class);
        return notificationMapperClass.notificationCommonToAddNewNotification(notificationCommonSaved);
    }


    public NotificationCommon addNewNotificationTest(NotificationCommon notificationCommon) {
        initDsl();
        return dsl.insertInto(Notificationcommon.NOTIFICATIONCOMMON)
                .set(dsl.newRecord(Notificationcommon.NOTIFICATIONCOMMON, notificationCommon))
                .returning()
                .fetchOne()
                .into(NotificationCommon.class);
    }

}

