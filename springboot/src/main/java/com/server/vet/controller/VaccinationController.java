package com.server.vet.controller;


import com.server.vet.dto.VaccinationDTO;
import com.server.vet.service.impl.VaccinationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/vaccination")
public class VaccinationController {

    private final VaccinationServiceImpl vaccinationService;

    @GetMapping("/all/{id}")
    List<VaccinationDTO> all(@PathVariable Long id){
        return vaccinationService.getAllVaccinationsByPetId(id);
    }

    @PostMapping()
    VaccinationDTO newTreatment(@RequestBody VaccinationDTO newVaccination) {
        return vaccinationService.createVaccination(newVaccination);
    }

    @GetMapping("/{id}")
    VaccinationDTO one(@PathVariable Long id) {
        return vaccinationService.getVaccinationById(id);
    }

    @PutMapping("/{id}")
    VaccinationDTO replaceVaccination(@RequestBody VaccinationDTO newVaccination, @PathVariable Long id) {
        return vaccinationService.updateVaccination(newVaccination,id);
    }

    @DeleteMapping("/{id}")
    void deleteVaccination(@PathVariable Long id) {
        vaccinationService.deleteVaccination(id);
    }

    @GetMapping("/vaccines")
    List<String> allVaccinesNames(){
        return vaccinationService.getAllVaccines();
    }

}
