package ru.socialnet.team29.serviceInterface;

import ru.socialnet.team29.model.FriendForFront;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FriendService {
    /**
     * Запрос на добавление друга
     * @param id идентификатор персоны
     * @param friendId идентификатор друга
     * @return true - в случае успеха
     */
    boolean addFriendRequest(Integer id, Integer friendId);

    /**
     * Подтверждение дружбы
     * @param id идентификатор персоны
     * @param friendId идентификатор друга
     * @return true - в случае успеха
     */
    boolean approveFriendship(Integer id, Integer friendId);

    /**
     * Получить всех друзей персоны
     * @param id идентификатор персоны
     * @param params набор параметров
     * @return список друзей класса FriendForFront
     */
    List<FriendForFront> getAllFriendsForPerson(Integer id, Map<String, String> params);

    /**
     * Удалдение друга
     * @param id идентификатор персоны
     * @param friendId идентификатор друга
     * @return true - в случае успеха
     */
    boolean deleteFriendship(Integer id, Integer friendId);

    /**
     * Проверка друга
     * @param id идентификатор персоны
     * @param friendId идентификатор друга
     * @return true - в случае успеха
     */
    boolean friendsByIdExists(Integer id, Integer friendId);
}
