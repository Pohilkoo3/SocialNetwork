package ru.socialnet.team29.interfaceDb;

import ru.socialnet.team29.model.FriendForFront;

import java.util.List;

/**
 * Интерфейс по дружеским отношениям
 */
public interface FriendInterface {
    /**
     * Запрос на добавление в друзья
     * @param id идентификатор персоны
     * @param friendId идентификатор желаемого друга
     * @return true - в случае успешного выполнения отправки запроса
     */
    Boolean addFriendRequest(Integer id, Integer friendId);

    /**
     * Одобрение дружеских отношений
     * @param id идентификатор персоны
     * @param friendId идентификатор нового друга
     * @return true - в случае успешного смены статуса на FRIEND
     */
    Boolean approveFriendship(Integer id, Integer friendId);

    /**
     * Получение всех друзей персоны
     * @param id идентификатор персоны
     * @param statusName
     * @return список всех друзей согласно фильтру params
     */
    List<FriendForFront> getFriendsByIdPerson(Integer id, String statusName);

    /**
     * Удалдение друга
     * @param id идентификатор персоны
     * @param friendId идентификатор друга
     * @return true - в случае успеха
     */
    Boolean deleteFriend(Integer id, Integer friendId);

    /**
     * Проверка друга
     * @param id идентификатор персоны
     * @param friendId идентификатор друга
     * @return true - в случае успеха
     */
    Boolean friendsByIdExists(Integer id, Integer friendId);
}
