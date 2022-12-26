package ru.socialnet.team29.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.socialnet.team29.model.Person;
import ru.socialnet.team29.security.CoreUserDetails;
import ru.socialnet.team29.serviceInterface.PersonService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final Person person;


    @Override
    public Person createNewTestPerson() {
        person.setId(5);
        person.setFirstName("Ivan");
        person.setLastName("Ivanov");
        person.setEmail("ivan@mail.ru");
        person.setPhone("8-917-338-20-20");
        person.setPhoto("link on url photo");
        person.setAbout("Was born in Russia");
        person.setCity("Moscow");
        person.setCountry("Russia");
        person.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwb0Bwby5jbyIsImV4cCI6MTY2OTQwNTUxNywiaW" +
                "F0IjoxNjY5MzY5NTE3fQ.a2JkHCAvfpM0776XVMkUgtGeiBcCTptEtQEj_U8qqso");
        person.setStatusCode("FRIEND");
        person.setRegDate(OffsetDateTime.now());
        person.setBirthDate(OffsetDateTime.now());
        person.setMessagesPermission("string");
        person.setLastOnlineTime(OffsetDateTime.now());
        person.setIsDeleted(false);
        person.setIsBlocked(false);
        person.setIsOnline(true);
        person.setPhotoId("3");
        person.setPhotoName("my photo");
        person.setRole("USER");
        person.setCreatedOn(OffsetDateTime.now());
        person.setUpdatedOn(OffsetDateTime.now());
        person.setPassword("Oleg1256$");

        return person;
    }

    public Person getPersonByEmail(String email){
        return person;
    }

    public Integer getIdPersonFromSecurityContext() {
        return ((CoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPerson().getId();

    }

    public Person getPersonFromSecurityContext() {
        return ((CoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPerson();

    }
}
