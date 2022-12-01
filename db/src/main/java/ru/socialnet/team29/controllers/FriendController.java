package ru.socialnet.team29.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.socialnet.team29.interfaceDb.FriendInterface;
import ru.socialnet.team29.model.FriendForFront;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FriendController {
    private final FriendInterface friendService;

    @GetMapping("/friends/request")
    Boolean addFriendRequest(@RequestParam Integer id, @RequestParam Integer friendId) {
        log.info("Получили запрос от core - Добавить друга => " + friendId);
        return friendService.addFriendRequest(id, friendId);
    }

    @PutMapping("/friends/{id}/approve")
    Boolean approveFriendship(@RequestParam Integer id, @RequestParam Integer friendId) {
        log.info("Получили запрос от core - Одобрить друга => " + friendId);
        return friendService.approveFriendship(id, friendId);
    }

    @GetMapping("/friends")
    List<FriendForFront> getFriendsByIdPerson(@RequestParam Integer id, @RequestParam String statusName) {
        log.info("Получили запрос от core - получить всех друзей => " + id.toString() + " со статусом " + statusName);
        return friendService.getFriendsByIdPerson(id, statusName);
    }

    @GetMapping("/friends/{id}")
    Boolean friendsByIdExists(@RequestParam Integer id, @RequestParam Integer friendId) {
        log.info("Получили запрос от core - проверка друга друзей id=" + friendId);
        return friendService.friendsByIdExists(id, friendId);
    }

    @DeleteMapping("/friends/{id}")
    Boolean deleteFriendship(@RequestParam Integer id, @RequestParam Integer friendId) {
        log.info("Получили запрос от core - удаление друга id=" + friendId);
        return friendService.deleteFriend(id, friendId);
    }
}
