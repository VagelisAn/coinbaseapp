package com.server.vet.service.impl;

import com.server.vet.dto.HistoryAppointmentDTO;
import com.server.vet.entity.HistoryAppointment;
import com.server.vet.mapper.HistoryAppointmentMapper;
import com.server.vet.repository.HistoryAppointmentRepository;
import com.server.vet.service.HistoryAppointmentService;
import com.server.vet.service.PetService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoryAppointmentServiceImpl implements HistoryAppointmentService {


    private final HistoryAppointmentRepository historyAppointmentRepository;
    private final HistoryAppointmentMapper historyAppointmentMapper;
    private final PetService petService;

    public List<HistoryAppointmentDTO> getAllByPetId(Long petId) {
        return historyAppointmentMapper.mapToHistoryAppointmentDTOs(
                historyAppointmentRepository.findByPetId(petId)
        );
    }

    @Transactional
    public HistoryAppointmentDTO createHistoryAppointment(HistoryAppointmentDTO newHistoryAppointmentDTO) {
        // Map DTO to Entity
    HistoryAppointment historyAppointment = historyAppointmentMapper.mapToHistoryAppointment(newHistoryAppointmentDTO);

    // Associate Pet if petId is present
    Optional.ofNullable(newHistoryAppointmentDTO.getPetId())
        .map(petService::getPetById)
        .ifPresent(historyAppointment::setPet);

    // Save the entity
    HistoryAppointment savedHistoryAppointment = historyAppointmentRepository.save(historyAppointment);

    // Map Entity to DTO and return
    return historyAppointmentMapper.mapToHistoryAppointmentDTO(savedHistoryAppointment);
    }

    public HistoryAppointmentDTO getHistoryAppointmentById(Long id) {
        return historyAppointmentRepository.findById(id)
                .map(historyAppointmentMapper::mapToHistoryAppointmentDTO)
                .orElse(null); // Consider throwing an exception for better error handling
    }

    public HistoryAppointmentDTO updateHistoryAppointment(HistoryAppointmentDTO updatedHistoryAppointmentDTO, Long id) {
        return historyAppointmentRepository.findById(id)
                .map(existingAppointment -> {
                    HistoryAppointment updatedAppointment = historyAppointmentMapper.mapToHistoryAppointment(updatedHistoryAppointmentDTO);
                    updatedAppointment.setId(existingAppointment.getId()); // Ensure ID is maintained
                    HistoryAppointment savedAppointment = historyAppointmentRepository.save(updatedAppointment);
                    return historyAppointmentMapper.mapToHistoryAppointmentDTO(savedAppointment);
                })
                .orElseGet(() -> {
                    HistoryAppointment newAppointment = historyAppointmentMapper.mapToHistoryAppointment(updatedHistoryAppointmentDTO);
                    HistoryAppointment savedAppointment = historyAppointmentRepository.save(newAppointment);
                    return historyAppointmentMapper.mapToHistoryAppointmentDTO(savedAppointment);
                });
    }

    public void deleteHistoryAppointmentById(Long id) {
        historyAppointmentRepository.deleteById(id);
    }
}
