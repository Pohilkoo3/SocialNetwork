package ru.socialnet.team29.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.socialnet.team29.model.FriendForFront;
import ru.socialnet.team29.serviceInterface.FriendService;
import ru.socialnet.team29.serviceInterface.feign.DBConnectionFeignInterface;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final DBConnectionFeignInterface feignInterfaceFriend;

    @Override
    public boolean addFriendRequest(Integer id, Integer friendId) {
        return feignInterfaceFriend.addFriendRequest(id, friendId);
    }

    @Override
    public boolean approveFriendship(Integer id, Integer friendId) {
        return feignInterfaceFriend.approveFriendship(id, friendId);
    }

    @Override
    public List<FriendForFront> getAllFriendsForPerson(Integer id, Map<String, String> params) {
        String statusName = params.getOrDefault("statusCode", "");
        return feignInterfaceFriend.getFriendsByIdPerson(id, statusName);
    }

    @Override
    public boolean deleteFriendship(Integer id, Integer friendId) {
        return feignInterfaceFriend.deleteFriendship(id, friendId);
    }

    @Override
    public boolean friendsByIdExists(Integer id, Integer friendId) {
        return feignInterfaceFriend.friendsByIdExists(id, friendId);
    }
}
