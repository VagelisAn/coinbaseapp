package com.server.vet.service.impl;



import com.server.vet.dto.TreatmentDTO;
import com.server.vet.entity.Drag;
import com.server.vet.entity.Pet;
import com.server.vet.entity.Treatment;
import com.server.vet.exception.ResourceNotFoundException;
import com.server.vet.mapper.DragMapper;
import com.server.vet.mapper.TreatmentMapper;
import com.server.vet.repository.DragRepository;
import com.server.vet.repository.TreatmentRepository;
import com.server.vet.service.PetService;
import com.server.vet.service.TreatmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final DragRepository dragRepository;
    private final TreatmentMapper treatmentMapper;
    private final DragMapper dragMapper;

    private final PetService petService;

    @Override
    public List<TreatmentDTO> getAllTreatmentsByPetId(Long petId) {
        return treatmentMapper.mapToTreatmentDTOs(treatmentRepository.findByPetId(petId));
    }

    @Override
    @Transactional
    public TreatmentDTO createTreatment(TreatmentDTO treatmentDTO) {
        Pet pet = petService.getPetById(treatmentDTO.getPetId());
        Treatment treatment = treatmentMapper.mapToTreatment(treatmentDTO);
        treatment.setPet(pet);

        Treatment savedTreatment = treatmentRepository.save(treatment);
        List<Drag> dragList = treatmentDTO.getDragsListDTO().stream()
                .map(dragMapper::mapToDrag)
                .peek(drag -> drag.setTreatment(savedTreatment))
                .toList();

        if (!dragList.isEmpty()) {
            savedTreatment.setDragList(dragRepository.saveAll(dragList));
        }
        return treatmentMapper.mapToTreatmentDTO(savedTreatment);
    }

    @Override
    public TreatmentDTO getTreatmentById(Long id) {
        return treatmentRepository.findById(id)
                .map(treatmentMapper::mapToTreatmentDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found for ID: " + id));
    }

    @Override
    public TreatmentDTO updateTreatment(TreatmentDTO treatmentDTO, Long id) {
        return treatmentRepository.findById(id)
                .map(existingTreatment -> {
                    Treatment updatedTreatment = treatmentMapper.mapToTreatment(treatmentDTO);
                    updatedTreatment.setId(existingTreatment.getId());
                    Treatment savedTreatment = treatmentRepository.save(updatedTreatment);
                    return treatmentMapper.mapToTreatmentDTO(savedTreatment);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found for ID: " + id));
    }

    @Override
    public void deleteTreatment(Long id) {
        if (!treatmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Treatment not found for ID: " + id);
        }
        treatmentRepository.deleteById(id);
    }

    @Override
    public List<String> getAllDrags() {
        return dragRepository.findAllDrag();
    }
}
