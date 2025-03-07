package com.server.vet.repository;


import com.server.vet.entity.Appointment;
import com.server.vet.entity.HistoryAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HistoryAppointmentRepository extends JpaRepository<HistoryAppointment, Long>{

    List<HistoryAppointment> findByPetId(Long id);
//    Optional<HistoryAppointment> findByOwnerId(String id);
}
