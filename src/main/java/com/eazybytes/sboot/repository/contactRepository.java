package com.eazybytes.sboot.repository;

import com.eazybytes.sboot.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface contactRepository extends CrudRepository<Contact,Integer> { }

