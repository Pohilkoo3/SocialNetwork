package ru.socialnet.team29.serviceInterface.feign.connections;

import org.springframework.stereotype.Component;
import ru.socialnet.team29.model.Notification;
import ru.socialnet.team29.serviceInterface.feign.interfaces.DBConnectionFeignInterfaceNotification;

import java.util.List;

@Component
public class FeignAnswerFromDbNotification implements DBConnectionFeignInterfaceNotification {


    @Override
    public Integer getCountNotification(Integer idPerson) {
        return null;
    }

    @Override
    public List<Notification> getAllNotificationsById(Integer idPerson) {
        return null;
    }
}
