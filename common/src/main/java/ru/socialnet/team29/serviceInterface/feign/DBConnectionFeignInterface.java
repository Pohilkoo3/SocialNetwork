package ru.socialnet.team29.serviceInterface.feign;

import java.security.Principal;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.socialnet.team29.model.FriendForFront;
import ru.socialnet.team29.model.Person;
import ru.socialnet.team29.model.PostDto;

@FeignClient(name = "db", url = "${server.db.port}")
public interface DBConnectionFeignInterface {

    @PostMapping(value = "/person")
    Person savePerson(@RequestBody Person person);

    @GetMapping(value = "/person")
    Person getPersonByEmail(@RequestParam String email);

    @GetMapping(value = "/posts")
    List<PostDto> getPostDto(@RequestParam String email);

    @GetMapping("/friends/request")
    Boolean addFriendRequest(@RequestParam Integer id, @RequestParam Integer friendId);

    @PutMapping("/friends/{id}/approve")
    Boolean approveFriendship(@RequestParam Integer id, @RequestParam Integer friendId);

    @GetMapping("/friends")
    List<FriendForFront> getFriendsByIdPerson(@RequestParam Integer id, @RequestParam String statusName);

    @DeleteMapping("/friends/{id}")
    Boolean deleteFriendship(@RequestParam Integer id, @RequestParam Integer friendId);

    @GetMapping("/friends/{id}")
    Boolean friendsByIdExists(@RequestParam Integer id, @RequestParam Integer friendId);
}