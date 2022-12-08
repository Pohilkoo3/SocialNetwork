package ru.socialnet.team29.serviceInterface.feign.connections;

import java.security.Principal;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import ru.socialnet.team29.model.FriendForFront;
import ru.socialnet.team29.model.Person;
import ru.socialnet.team29.model.PostDto;
import ru.socialnet.team29.serviceInterface.feign.DBConnectionFeignInterface;
import ru.socialnet.team29.serviceInterface.feign.interfaces.DBConnectionFeignInterface;

import java.util.List;

@Component
public class FeignAnswerFromDb implements DBConnectionFeignInterface {

    @Override
    public Person savePerson(Person person) {
        return null;
    }

    @Override
    public Person getPersonByEmail(String email) {
        return null;
    }

    @Override
    public List<PostDto> getPostDto(@RequestParam String email) {
        return null;
    }

    @Override
    public Boolean addFriendRequest(Integer id, Integer friendId) {
        return null;
    }

    @Override
    public Boolean approveFriendship(Integer id, Integer friendId) {
        return null;
    }

    @Override
    public List<FriendForFront> getFriendsByIdPerson(Integer id, String statusName) {
        return null;
    }

    @Override
    public Boolean deleteFriendship(Integer id, Integer friendId) {
        return null;
    }

    @Override
    public Boolean friendsByIdExists(Integer id, Integer friendId) {
        return null;
    }
}
