package com.server.vet.repository;



import com.server.vet.entity.Drag;
import com.server.vet.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DragRepository extends JpaRepository<Drag, Long>{
    List<Drag> findByTreatmentId(Long id);
    @Query(value = "SELECT DISTINCT drag FROM drag", nativeQuery = true)
    List<String> findAllDrag();
}
