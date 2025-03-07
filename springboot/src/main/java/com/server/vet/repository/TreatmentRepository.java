package com.server.vet.repository;



import com.server.vet.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long>{
    List<Treatment> findByPetId(Long id);
    @Query(value = "SELECT DISTINCT drag FROM treatment", nativeQuery = true)
    List<String> findAllDrag();
}
