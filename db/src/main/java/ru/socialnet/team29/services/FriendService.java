package ru.socialnet.team29.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.socialnet.team29.domain.tables.Person;
import ru.socialnet.team29.domain.tables.records.FriendshipRecord;
import ru.socialnet.team29.domain.tables.records.PersonRecord;
import ru.socialnet.team29.interfaceDb.FriendInterface;
import ru.socialnet.team29.mappers.FriendMapperImpl;
import ru.socialnet.team29.model.FriendForFront;
import ru.socialnet.team29.repositories.FriendRepository;
import ru.socialnet.team29.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Сервис по предоставлению всей информации по дружеским отношениям
 */
@Service
@RequiredArgsConstructor
public class FriendService implements FriendInterface {
    private final FriendRepository friendRepository;
    private final PersonRepository personRepository;
    private final FriendMapperImpl friendMapper;

    @Override
    public Boolean addFriendRequest(Integer id, Integer friendId) {
        return friendRepository.addFriendRequest(id, friendId);
    }

    @Override
    public Boolean approveFriendship(Integer id, Integer friendId) {
        return friendRepository.approveFriendship(id, friendId);
    }

    @Override
    public List<FriendForFront> getFriendsByIdPerson(Integer id, String statusName) {
        List<FriendshipRecord> friendRecords =  friendRepository.getFriendsByIdPerson(id, statusName);
        List<PersonRecord> personRecords = new ArrayList<>();
        // Фильтруем по статусу
//        String statusCode = params.getOrDefault("statusCode", "");
        if (!statusName.equals("")) {
            Long statusId = friendRepository.getFriendshipStatusRecord(statusName).getId();
            List<Integer> friendIds = friendRecords.stream()
                    .filter(friend -> friend.getStatusId().equals(statusId.toString()))
                    .map(rec -> Integer.valueOf(rec.getDstPersonId())).toList();
            personRecords = personRepository
                    .findAll(Person.PERSON.ID.in(friendIds));
        }
        return friendMapper.PersonRecordToFriendForFront(personRecords)
                .stream().peek(friend -> friend.setStatusCode(statusName)).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteFriend(Integer id, Integer friendId) {
        return friendRepository.deleteFriendship(id, friendId);
    }

    @Override
    public Boolean friendsByIdExists(Integer id, Integer friendId) {
        return friendRepository.friendsByIdExists(id, friendId);
    }
}
