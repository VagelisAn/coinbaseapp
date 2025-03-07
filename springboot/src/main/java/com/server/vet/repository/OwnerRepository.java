package com.server.vet.repository;

import com.server.vet.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
     @Query("SELECT o FROM Owner o JOIN o.petList p WHERE p.id = :petId")
    Optional<Owner> findByPetId(@Param("petId") Long petId);

     Optional<Owner> findByFirstnameAndLastnameOrEmail(String firstname, String lastname, String email);

    Optional<Owner> findByKeycloakId(String keycloakId);
}