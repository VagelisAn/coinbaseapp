package com.server.vet.repository;


import com.server.vet.entity.Appointment;
import com.server.vet.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long>{

    List<Reminder> findByPetId(Long id);
//    Optional<Reminder> findByOwnerId(String id);
}
