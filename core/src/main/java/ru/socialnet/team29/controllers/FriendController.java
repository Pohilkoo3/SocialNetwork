package ru.socialnet.team29.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.socialnet.team29.answers.AnswerListFriendsForPerson;
import ru.socialnet.team29.model.FriendForFront;
import ru.socialnet.team29.service.FriendServiceImpl;
import ru.socialnet.team29.service.PersonServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class FriendController {

    private final FriendServiceImpl friendService;
    private final PersonServiceImpl personService;

    /**
     * Добавление друга
     */
    @PostMapping("/friends/{id}/request")
    public ResponseEntity<Void> addFriendRequest(@PathVariable(value = "id") Integer friendId) {
        if (friendId != null) {
            log.info("Add friend id=" + friendId.toString());
//          TODO  int id = personService.getIdPersonFromSecurityContext();
            int id = 1;
            boolean isAdded = friendService.addFriendRequest(id, friendId);
            if (!isAdded) {
                log.info("The friend is not added");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            log.info("Add friend id=null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Подтверждение дружбы
     */
    @PutMapping("/friends/{id}/approve")
    public ResponseEntity<Void> approveFriendship(@PathVariable(value = "id") Integer friendId) {
        if (friendId != null) {
            log.info("Approve friend id=" + friendId.toString());
//          TODO  int id = personService.getIdPersonFromSecurityContext();
            int id = 1;
            boolean isAdded = friendService.approveFriendship(id, friendId);
            if (!isAdded) {
                log.info("The friend is not Approved");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            log.info("The friend id=null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Получение всех друзей
     */
    @GetMapping("/friends")
    public ResponseEntity<AnswerListFriendsForPerson> getFriendsByIdPerson(@RequestParam HashMap<String,String> params) {
        log.info("Получаем запрос от фронта на получение всех друзей для  " + SecurityContextHolder.getContext().getAuthentication().getName() + " с параметрами " + params.toString());
//      TODO  int id = personService.getIdPersonFromSecurityContext();
        int id = 1;
//      TODO Нужно поделить по страницам данные
        Integer sizePage = Integer.valueOf(params.getOrDefault("size", "20"));
        List<FriendForFront> listFriends = friendService.getAllFriendsForPerson(id, params);
        AnswerListFriendsForPerson answer =
            AnswerListFriendsForPerson.builder()
                    .content(listFriends)
                    .empty(listFriends.size() == 0)
                    .numberOfElements(listFriends.size())
                    .totalElements(listFriends.size())
                    .sort(new ArrayList<>())
                    .totalPages((int) Math.ceil(listFriends.size() / sizePage))
                    .pageable(AnswerListFriendsForPerson.FriendPageable.builder()
                            .pageSize(sizePage)
                            .build())
                    .build();
        return new ResponseEntity<>(answer, HttpStatus.OK) ;
    }

    /**
     * Получить количество друзей
     */
    @GetMapping("/friends/count")
    public String friendsCount() {
        log.info("Получаем запрос от фронта на получение количества всех друзей для  " + SecurityContextHolder.getContext().getAuthentication().getName());
//      TODO  int id = personService.getIdPersonFromSecurityContext();
        int id = 1;
//      TODO Сделать запрос на получение количества друзей отдельным сервисом (в другом спринте)
        HashMap<String, String> params = new HashMap<>();
        params.put("statusCode", "FRIEND");
        List<FriendForFront> listFriends = friendService.getAllFriendsForPerson(id, params);
        return String.valueOf(listFriends.size());
    }

    /**
     * Имеется ли друг с id
     */
    @GetMapping("/friends/{id}")
    public ResponseEntity<Void> friendsByIdExists(@PathVariable(value = "id") Integer friendId) {
        if (friendId != null) {
            log.info("Get friend by id=" + friendId.toString());
//          TODO  int id = personService.getIdPersonFromSecurityContext();
            int id = 1;
            boolean isExists = friendService.friendsByIdExists(id, friendId);
            if (!isExists) {
                log.info("The friend is not deleted");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            log.info("The deleted friend id=null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Удаление друга
     */
    @DeleteMapping("/friends/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable(value = "id") Integer friendId) {
        if (friendId != null) {
            log.info("Delete friend id=" + friendId.toString());
//          TODO  int id = personService.getIdPersonFromSecurityContext();
            int id = 1;
            boolean isDeleted = friendService.deleteFriendship(id, friendId);
            if (!isDeleted) {
                log.info("The friend is not deleted");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            log.info("The deleted friend id=null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
