package com.eazybytes.sboot.service;

import com.eazybytes.sboot.constants.sbootConstants;
import com.eazybytes.sboot.model.Contact;
import com.eazybytes.sboot.model.Person;
import com.eazybytes.sboot.model.Roles;
import com.eazybytes.sboot.repository.contactRepository;
import com.eazybytes.sboot.repository.PersonRepository;
import com.eazybytes.sboot.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(sbootConstants.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}
