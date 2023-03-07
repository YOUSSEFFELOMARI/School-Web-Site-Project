package com.eazybytes.sboot.repository;

import com.eazybytes.sboot.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface contactRepository extends CrudRepository<Contact,Integer> {
    List<Contact> findByStatus(String status);

    //@Query("SELECT c FROM Contact c WHERE c.status= :status")//JPQL Query
    @Query(value = "SELECT * FROM contact_msg  WHERE status = :status",nativeQuery = true)
    Page<Contact> findByStatus(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id);

}

