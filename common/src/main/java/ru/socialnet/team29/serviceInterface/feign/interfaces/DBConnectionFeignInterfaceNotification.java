package ru.socialnet.team29.serviceInterface.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.socialnet.team29.model.Notification;
import ru.socialnet.team29.model.Person;

import java.util.List;

@FeignClient(name = "dbNotification", url = "${server.db.port}")
public interface DBConnectionFeignInterfaceNotification {

    @GetMapping(value = "/notifications/count")
    Integer getCountNotification(@RequestParam Integer idPerson);
    @GetMapping(value = "/notifications")
    List<Notification> getAllNotificationsById(@RequestParam Integer idPerson);
}
