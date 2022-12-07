package ru.socialnet.team29.mappers;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.socialnet.team29.answers.AddNewNotification;
import ru.socialnet.team29.answers.NotificationForFront;
import ru.socialnet.team29.model.NotificationCommon;
import ru.socialnet.team29.model.enums.NotificationType;
import ru.socialnet.team29.payloads.AddNotificationPayload;
import ru.socialnet.team29.repositories.PersonRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NotificationMapperClass{
    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(@Lazy PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public AddNewNotification notificationCommonToAddNewNotification(NotificationCommon notificationCommon){
            return AddNewNotification.builder()
                    .id(String.valueOf(notificationCommon.getId()))
                    .notificationType(NotificationType.getTypeEnum(notificationCommon.getTypeId()))
                    .time(notificationCommon.getSentTime())
                    .authorId(String.valueOf(personRepository.findIdPersonByEmail(notificationCommon.getContact())))
                    .userId(String.valueOf(notificationCommon.getPersonId()))
                    .content("")
                    .isStatusSent(true)
                    .build();
        }

    public NotificationCommon addNotificationPayloadToNotificationCommon(AddNotificationPayload payload){
        return NotificationCommon.builder()
                .typeId (payload.getNotificationTypeId())
                .sentTime(LocalDateTime.now())
                .personId(Integer.parseInt(payload.getUserId()))
                .contact(personRepository.findEmailByPersonId(Integer.parseInt(payload.getAuthorId())))
                .build();
    }

    public NotificationForFront notificationCommonToNotificationForFront(NotificationCommon notificationCommon){
        return NotificationForFront.builder()
                .id(notificationCommon.getId())
                .content("")
                .person(personRepository.findOnePersonModelByEmail(notificationCommon.getContact()))
                .notificationType(NotificationType.getTypeEnum(notificationCommon.getTypeId()))
                .sentTime(notificationCommon.getSentTime())
                .build();
    }

    public List<NotificationForFront>  listNotificationCommonToListNotificationForFront(List<NotificationCommon> listNotificationCommon){
        List<NotificationForFront> result = new ArrayList<>();
        for (NotificationCommon notification : listNotificationCommon) {
        result.add(notificationCommonToNotificationForFront(notification));
        }
        return result;
    }
}
