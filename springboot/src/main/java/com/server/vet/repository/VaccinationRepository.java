package com.server.vet.repository;


import com.server.vet.entity.Appointment;
import com.server.vet.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long>{

    List<Vaccination> findByPetId(Long id);
//    Optional<Vaccination> findByOwnerId(String id);
    @Query(value = "SELECT DISTINCT name FROM vaccination", nativeQuery = true)
    List<String> findAllVaccines();

    @Query("SELECT v FROM Vaccination v WHERE v.pet.id = :id ORDER BY v.repeatDate DESC ")
    List<Vaccination> findVaccinationsByIdOrdered(@Param("id") Long id);
}
