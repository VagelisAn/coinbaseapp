package com.server.vet.service.impl;

import com.server.vet.dto.WeightDTO;
import com.server.vet.entity.Pet;
import com.server.vet.entity.Weight;
import com.server.vet.exception.ResourceNotFoundException;
import com.server.vet.mapper.PetMapper;
import com.server.vet.mapper.WeightMapper;
import com.server.vet.repository.PetRepository;
import com.server.vet.repository.WeightRepository;
import com.server.vet.service.WeightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeightServiceImpl implements WeightService {

    private final WeightRepository weightRepository;
    private final WeightMapper weightMapper;
    private final PetMapper petMapper;
    private final PetRepository petRepository;

    /**
     * Retrieve all weight records associated with a specific pet ID.
     *
     * @param petId the ID of the pet
     * @return a list of WeightDTOs
     */
    public List<WeightDTO> getAllWeightsByPetId(Long petId) {
        return weightMapper.mapToWeightDTOs(weightRepository.findByPetId(petId));
    }

    /**
     * Create and store a new weight record.
     *
     * @param newWeightDTO the data transfer object for the new weight
     * @return the created WeightDTO
     */
    public WeightDTO createWeight(WeightDTO newWeightDTO) {
        Weight newWeight = weightMapper.mapToWeight(newWeightDTO);

          Pet pet = petRepository.findById(newWeightDTO.getPetId())
        .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + newWeightDTO.getPetId()));
        newWeight.setPet(pet);

        Weight savedWeight = weightRepository.save(newWeight);
        return weightMapper.mapToWeightDTO(savedWeight);
    }

    /**
     * Retrieve a single weight record by its ID.
     *
     * @param id the ID of the weight record
     * @return an Optional of WeightDTO
     */
    public Optional<WeightDTO> getWeightById(Long id) {
        return weightRepository.findById(id)
                .map(weightMapper::mapToWeightDTO);
    }

    /**
     * Replace an existing weight record or create a new one if not found.
     *
     * @param newWeightDTO the data transfer object for the weight
     * @param id the ID of the weight to replace
     * @return the updated or created WeightDTO
     */
    public WeightDTO updateOrCreateWeight(WeightDTO newWeightDTO) {
        return weightRepository.findById(newWeightDTO.getPetId())
                .map(existingWeight -> {
                    Weight updatedWeight = weightMapper.mapToWeight(newWeightDTO);
                    updatedWeight.setId(existingWeight.getId());
                    Weight savedWeight = weightRepository.save(updatedWeight);
                    return weightMapper.mapToWeightDTO(savedWeight);
                })
                .orElseGet(() -> {
                    Weight newWeight = weightMapper.mapToWeight(newWeightDTO);
                    Weight savedWeight = weightRepository.save(newWeight);
                    return weightMapper.mapToWeightDTO(savedWeight);
                });
    }

    /**
     * Delete a weight record by its ID.
     *
     * @param id the ID of the weight to delete
     */
    public void deleteWeight(Long id) {
        weightRepository.deleteById(id);
    }
}
