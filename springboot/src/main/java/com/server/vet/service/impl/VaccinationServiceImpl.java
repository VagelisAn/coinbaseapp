package com.server.vet.service.impl;


import com.server.vet.dto.VaccinationDTO;
import com.server.vet.entity.Pet;
import com.server.vet.entity.Vaccination;
import com.server.vet.exception.ResourceNotFoundException;
import com.server.vet.mapper.PetMapper;
import com.server.vet.mapper.VaccinationMapper;
import com.server.vet.repository.PetRepository;
import com.server.vet.repository.VaccinationRepository;
import com.server.vet.service.PetService;
import com.server.vet.service.VaccinationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccinationMapper vaccinationMapper;

    private final PetService petService;

    @Override
    public List<VaccinationDTO> getAllVaccinationsByPetId(Long petId) {
        return vaccinationMapper.mapToVaccinationDTOs(vaccinationRepository.findVaccinationsByIdOrdered(petId));
    }

    @Override
    @Transactional
    public VaccinationDTO createVaccination(VaccinationDTO vaccinationDTO) {

        Pet pet = petService.getPetById(vaccinationDTO.getPetId());

        Vaccination vaccination = vaccinationMapper.mapToVaccination(vaccinationDTO);
        vaccination.setPet(pet);
        Vaccination savedVaccination = vaccinationRepository.save(vaccination);
        return vaccinationMapper.mapToVaccinationDTO(savedVaccination);
    }

    @Override
    public VaccinationDTO getVaccinationById(Long id) {
        return vaccinationRepository.findById(id)
                .map(vaccinationMapper::mapToVaccinationDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccination not found for ID: " + id));
    }

    @Override
    public VaccinationDTO updateVaccination(VaccinationDTO vaccinationDTO, Long id) {
        return vaccinationRepository.findById(id)
                .map(existingVaccination -> {
                    Vaccination updatedVaccination = vaccinationMapper.mapToVaccination(vaccinationDTO);
                    updatedVaccination.setId(existingVaccination.getId());
                    Vaccination savedVaccination = vaccinationRepository.save(updatedVaccination);
                    return vaccinationMapper.mapToVaccinationDTO(savedVaccination);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Vaccination not found for ID: " + id));
    }

    @Override
    public void deleteVaccination(Long id) {
        if (!vaccinationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vaccination not found for ID: " + id);
        }
        vaccinationRepository.deleteById(id);
    }

    @Override
    public List<String> getAllVaccines() {
        return vaccinationRepository.findAllVaccines();
    }
}
