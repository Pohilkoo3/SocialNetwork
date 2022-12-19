package ru.socialnet.team29.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.exception.NoDataFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.socialnet.team29.interfaceDb.PersonInterfaceDB;
import ru.socialnet.team29.repository.PersonRepository;
import ru.socialnet.team29.domain.tables.records.PersonRecord;
import ru.socialnet.team29.mappers.PersonMapper;
import ru.socialnet.team29.model.Person;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService implements PersonInterfaceDB {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public Person getPersonByEmail(String email) {
        PersonRecord person = personRepository.findPersonByEmail(email);
        if (person != null && !person.getIsDeleted()) {
            return personMapper.PersonRecordToPerson(person);
        } else {
            throw new NoDataFoundException("No users found such email");
        }
    }

    public Person getPersonByToken(String token) {
        PersonRecord person = personRepository.findPersonByToken(token);
        if (person != null) {
            return personMapper.PersonRecordToPerson(person);
        } else {
            throw new NoDataFoundException("No users found such token");
        }
    }

    @Override
    public String findEmailByPersonId(String id) {
        return String.valueOf(personRepository.findEmailByPersonId(id));
        // todo метод возвращает не то, что нужно?
    }


    /**
     * @deprecated Вместо этого метода предлагаются более подходящие методы </br>
     * {@link PersonService#isExist(int)},
     * {@link PersonService#findByIdList(List)}
     * @param condition условие выборки
     * @return список аккаунтов
     */
    @Deprecated
    public List<PersonRecord> findAll(Condition condition) {
        return personRepository.findAll(condition);
    }

    public int savePerson(Person person) {
        log.info("Сохраняем новый аккаунт {}", person.getEmail());
        return personRepository.insert(personMapper.PersonToPersonRecord(person));
    }

    public Person update(Person person) {
        log.info("Обновляем данные аккаунта {}", person.getId());
        PersonRecord personRecord = personRepository.update(personMapper.PersonToPersonRecord(person));
        return personMapper.PersonRecordToPerson(personRecord);
    }

    public boolean delete(int id) {
        log.info("Удаляем аккаунт id={}", id);
        return personRepository.delete(id);
    }

    public Person findById(int id) {
        log.info("Запрос данных аккаунта id={}", id);
        var record = personRepository.findById(id);
        if (record.getIsDeleted()) {
            log.info("Аккаунт удалён");
            return null;
        }
        return personMapper.PersonRecordToPerson(personRepository.findById(id));
    }

    public List<Person> findByPageableTerm(Pageable pageable) {
        log.info("Запрос списка аккаунтов {}", pageable.toString());
        return personMapper.PersonRecordsToPersons(personRepository.findByPageableTerm(pageable));
    }

    public List<PersonRecord> findByIdList(List<Integer> ids) {
        log.info("Запрос списка аккаунтов {}", ids);
        return personRepository.findByIdList(ids);
    }

    public boolean isExist(int id) {
        log.info("Запрос на существование профиля id={}", id);
        return personRepository.isExist(id);
    }

    public void setOnline(String email) {
        var person = getPersonByEmail(email);
        person.setIsOnline(true);
        person.setLastOnlineTime(OffsetDateTime.now());
        personRepository.update(personMapper.PersonToPersonRecord(person));
    }

    public void setOffline(int id) {
        var person = findById(id);
        person.setIsOnline(false);
        person.setLastOnlineTime(OffsetDateTime.now());
        personRepository.update(personMapper.PersonToPersonRecord(person));
    }
}
