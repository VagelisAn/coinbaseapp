package com.server.vet.service;

import com.server.vet.dto.WeightDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface WeightService {
    List<WeightDTO> getAllWeightsByPetId(Long id) throws IOException;

    WeightDTO createWeight(WeightDTO newWeightDTO);

    Optional<WeightDTO> getWeightById(Long id);

    WeightDTO updateOrCreateWeight(WeightDTO newWeightDTO);

    void deleteWeight(Long id);

}
