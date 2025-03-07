package com.server.vet.repository;



import com.server.vet.entity.Owner;
import com.server.vet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

    List<Pet> findByOwnerId(Long id);

    @Query(value = "SELECT DISTINCT breed FROM pet where pet.breed is not null", nativeQuery = true)
    List<String> findAllBreeds();

    @Query(value = "SELECT DISTINCT species FROM pet where pet.species is not null", nativeQuery = true)
    List<String> findAllSpecies();

    @Query(value = "SELECT DISTINCT color FROM pet where pet.color is not null", nativeQuery = true)
    List<String> findAllColors();


//    Pet save(Pet pet);
}
