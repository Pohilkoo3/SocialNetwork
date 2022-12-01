package ru.socialnet.team29.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.socialnet.team29.domain.tables.Friendship;
import ru.socialnet.team29.domain.tables.FriendshipStatusTable;
import ru.socialnet.team29.domain.tables.records.FriendshipRecord;
import ru.socialnet.team29.domain.tables.records.FriendshipStatusTableRecord;
import ru.socialnet.team29.domain.tables.records.PostCommentRecord;
import ru.socialnet.team29.services.DslContextCustom;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class FriendRepository {

    private final DslContextCustom dslContextCustom;
    private static DSLContext dsl;

    private void initDsl() {
        if (dsl == null) {
            dsl = dslContextCustom.initDslContext();
        }
    }

    /**
     * Запрос на добавление друга
     * @param id
     * @param friendId
     * @return
     */
    public Boolean addFriendRequest(Integer id, Integer friendId) {
        initDsl();
        Friendship friendship = Friendship.FRIENDSHIP;
        // Удостовериться, что персона с friendId существует
        boolean isExistsFriend =
            dsl.selectFrom(friendship)
                .where(friendship.ID.eq(Long.valueOf(friendId)))
                .fetch()
                .size() == 1;
        if (!isExistsFriend) {
            return false;
        }
        // Заполнить таблицу friendship
        FriendshipStatusTableRecord statusREQUEST = getFriendshipStatusRecord("REQUEST");
        boolean isExistsFriendship =
            dsl.selectFrom(friendship)
                    .where(friendship.SRC_PERSON_ID.eq(id.toString()), friendship.DST_PERSON_ID.eq(friendId.toString()))
                    .fetch()
                    .size() > 0;
        if (isExistsFriendship) {
            dsl.update(friendship)
                    .set(friendship.STATUS_ID, statusREQUEST.getId().toString())
                    .where(friendship.SRC_PERSON_ID.eq(id.toString()), friendship.DST_PERSON_ID.eq(friendId.toString()))
                    .execute();
        } else {
            dsl.insertInto(friendship)
                    .set(friendship.SRC_PERSON_ID, id.toString())
                    .set(friendship.DST_PERSON_ID, friendId.toString())
                    .set(friendship.STATUS_ID, statusREQUEST.getId().toString())
                    .execute();
        }
        return true;
    }

    /**
     * Подтверждение дружбы
     * @param id
     * @param friendId
     * @return
     */
    public Boolean approveFriendship(Integer id, Integer friendId) {
        initDsl();
        Friendship friendship = Friendship.FRIENDSHIP;
        // Удостовериться, что персона с friendId существует
        boolean isExistsFriend =
                dsl.selectFrom(friendship)
                    .where(friendship.ID.eq(Long.valueOf(friendId)))
                    .fetch()
                    .size() == 1;
        if (!isExistsFriend) {
            return false;
        }
        // Заполнить таблицу friendship
        FriendshipStatusTableRecord statusREQUEST = getFriendshipStatusRecord("REQUEST");
        FriendshipStatusTableRecord statusFRIEND = getFriendshipStatusRecord("FRIEND");
        boolean isExistsFriendship =
                dsl.selectFrom(friendship)
                    .where(
                        friendship.SRC_PERSON_ID.eq(id.toString())
                        .and(friendship.DST_PERSON_ID.eq(friendId.toString()))
                        .and(friendship.STATUS_ID.eq(statusREQUEST.getId().toString())))
                    .fetch()
                    .size() > 0;
        if (isExistsFriendship) {
                dsl.update(friendship)
                    .set(friendship.STATUS_ID, statusREQUEST.getId().toString())
                    .where(
                        friendship.SRC_PERSON_ID.eq(id.toString())
                        .and(friendship.DST_PERSON_ID.eq(friendId.toString()))
                        .and(friendship.STATUS_ID.eq(statusFRIEND.getId().toString())))
                    .execute();
        } else {
            log.info("Не найден друг с id(" + friendId + ") и статусом 'REQUEST'");
            return false;
        }
        return true;
    }

    /**
     * Выяснение статуса отношений
     * @param statusName
     * @return
     */
    public FriendshipStatusTableRecord getFriendshipStatusRecord(String statusName) {
        initDsl();
        return dsl.selectFrom(FriendshipStatusTable.FRIENDSHIP_STATUS_TABLE)
                .where(FriendshipStatusTable.FRIENDSHIP_STATUS_TABLE.NAME.eq(statusName))
                .fetchOne()
                .into(FriendshipStatusTableRecord.class);
    }

    /**
     * Получение списка записей о друзьях из БД
     * @param id
     * @param statusName
     * @return
     */
    public List<FriendshipRecord> getFriendsByIdPerson(Integer id, String statusName) {
        initDsl();
        FriendshipStatusTableRecord statusFRIEND = getFriendshipStatusRecord(statusName);
        Friendship friendship = Friendship.FRIENDSHIP;
        return dsl.selectFrom(friendship)
            .where(
                friendship.STATUS_ID.eq(statusFRIEND.getId().toString())
                .and(friendship.SRC_PERSON_ID.eq(id.toString())))
            .fetch()
            .into(FriendshipRecord.class);
    }

    /**
     * Удалдение друга
     * @param id идентификатор персоны
     * @param friendId идентификатор друга
     * @return true - в случае успеха
     */
    public Boolean deleteFriendship(Integer id, Integer friendId) {
        initDsl();
        Friendship friendship = Friendship.FRIENDSHIP;
        return dsl.deleteFrom(friendship)
                .where(
                    friendship.SRC_PERSON_ID.eq(id.toString())
                    .and(friendship.DST_PERSON_ID.eq(friendId.toString())))
                .execute() == 1;
    }

    /**
     * Проверка друга
     * @param id идентификатор персоны
     * @param friendId идентификатор друга
     * @return true - в случае успеха
     */
    public Boolean friendsByIdExists(Integer id, Integer friendId) {
        initDsl();
        Friendship friendship = Friendship.FRIENDSHIP;
        return dsl.selectFrom(friendship)
                .where(
                    friendship.SRC_PERSON_ID.eq(id.toString())
                    .and(friendship.DST_PERSON_ID.eq(friendId.toString())))
                .fetch()
                .size() == 1;
    }
}
