package com.server.vet.service;



import com.server.vet.dto.TreatmentDTO;
import com.server.vet.dto.VaccinationDTO;

import java.io.IOException;
import java.util.List;

public interface VaccinationService {
    List<VaccinationDTO> getAllVaccinationsByPetId(Long id) throws IOException;

    VaccinationDTO createVaccination(VaccinationDTO newVaccination);

    VaccinationDTO getVaccinationById(Long id);

    VaccinationDTO updateVaccination(VaccinationDTO newVaccination, Long id);

    void deleteVaccination(Long id);

    List<String> getAllVaccines();
}
