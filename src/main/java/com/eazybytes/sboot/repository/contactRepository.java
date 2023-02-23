package com.eazybytes.sboot.repository;

import com.eazybytes.sboot.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface contactRepository extends CrudRepository<Contact,Integer> {
    List<Contact> findByStatus(String stauts);

}

