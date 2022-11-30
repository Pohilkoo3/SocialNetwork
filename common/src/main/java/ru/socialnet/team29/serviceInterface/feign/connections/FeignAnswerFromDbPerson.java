package ru.socialnet.team29.serviceInterface.feign.connections;

import org.springframework.stereotype.Component;
import ru.socialnet.team29.model.Person;
import ru.socialnet.team29.serviceInterface.feign.interfaces.DBConnectionFeignInterface;

@Component
public class FeignAnswerFromDbPerson implements DBConnectionFeignInterface {

    @Override
    public Person savePerson(Person person) {
        return null;
    }

    @Override
    public Person getPersonByEmail(String email) {
        return null;
    }
}
