package com.server.vet.controller;


import com.server.vet.dto.WeightDTO;
import com.server.vet.exception.ResourceNotFoundException;
import com.server.vet.service.impl.WeightServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/weight")
public class WeightController {


    private final WeightServiceImpl weightService;


    @GetMapping("/all/{id}")
    List<WeightDTO> all(@PathVariable Long id){
        return weightService.getAllWeightsByPetId(id);
    }

    @PostMapping()
    WeightDTO newWeight(@RequestBody WeightDTO newWeight) {
        return weightService.createWeight(newWeight);
    }

    @GetMapping("/{id}")
    WeightDTO one(@PathVariable Long id) {
        return weightService.getWeightById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Weight not found for ID: " + id));
    }

    @PutMapping("/{id}")
    WeightDTO replaceWeight(@RequestBody WeightDTO newWeight) {
        return weightService.updateOrCreateWeight(newWeight);
    }

    @DeleteMapping("/{id}")
    void deleteWeight(@PathVariable Long id) {
        weightService.deleteWeight(id);
    }

}
