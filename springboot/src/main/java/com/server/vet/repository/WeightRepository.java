package com.server.vet.repository;



import com.server.vet.entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WeightRepository extends JpaRepository<Weight, Long>{
    List<Weight> findByPetId(Long id);
}
