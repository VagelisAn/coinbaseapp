package com.server.vet.controller;


import com.server.vet.dto.TreatmentDTO;
import com.server.vet.service.impl.TreatmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/treatments")
public class TreatmentController {

    private final TreatmentServiceImpl treatmentsService;


    @GetMapping("/all/{id}")
    List<TreatmentDTO> all(@PathVariable Long id){
        return treatmentsService.getAllTreatmentsByPetId(id);
    }

    @PostMapping()
    TreatmentDTO newTreatment(@RequestBody TreatmentDTO newTreatment) {
        return treatmentsService.createTreatment(newTreatment);
    }

    @GetMapping("/{id}")
    TreatmentDTO one(@PathVariable Long id) {
        return treatmentsService.getTreatmentById(id);
    }

    @PutMapping("/{id}")
    TreatmentDTO replaceTreatment(@RequestBody TreatmentDTO newTreatment, @PathVariable Long id) {
        return treatmentsService.updateTreatment(newTreatment,id);
    }

    @DeleteMapping("/{id}")
    void deleteTreatment(@PathVariable Long id) {
        treatmentsService.deleteTreatment(id);
    }

    @GetMapping("/drags")
    List<String> drags() {
        return treatmentsService.getAllDrags();
    }
}
