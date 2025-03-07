package com.server.vet.repository;


import com.server.vet.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

    List<Appointment> findByPetId(Long id);
//    Optional<Appointment> findByOwnerId(String id);
}
