package com.server.vet.service;



import com.server.vet.dto.TreatmentDTO;
import com.server.vet.entity.Pet;


import java.io.IOException;
import java.util.List;

public interface TreatmentService {
    List<TreatmentDTO> getAllTreatmentsByPetId(Long id) throws IOException;

    TreatmentDTO createTreatment(TreatmentDTO newTreatment);

    TreatmentDTO getTreatmentById(Long id);

    TreatmentDTO updateTreatment(TreatmentDTO newTreatment, Long id);

    void deleteTreatment(Long id);

    List<String> getAllDrags();

}
