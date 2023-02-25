package com.eazybytes.sboot.repository;

import com.eazybytes.sboot.model.Contact;
import com.eazybytes.sboot.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles getByRoleName(String roleName);
}
