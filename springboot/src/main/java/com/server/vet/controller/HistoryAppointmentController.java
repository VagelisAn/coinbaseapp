package com.server.vet.controller;

import com.server.vet.dto.HistoryAppointmentDTO;
import com.server.vet.service.impl.HistoryAppointmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/history-app")
public class HistoryAppointmentController {

    private final HistoryAppointmentServiceImpl historyAppointmentService;

    @GetMapping("/all/{id}")
    List<HistoryAppointmentDTO> all(@PathVariable Long id){
        return historyAppointmentService.getAllByPetId(id);
    }

    @PostMapping()
    HistoryAppointmentDTO newHistoryAppointment(@RequestBody HistoryAppointmentDTO newTreatment) {
        return historyAppointmentService.createHistoryAppointment(newTreatment);
    }

    @GetMapping("/{id}")
    HistoryAppointmentDTO one(@PathVariable Long id) {
        return historyAppointmentService.getHistoryAppointmentById(id);
    }

    @PutMapping("/{id}")
    HistoryAppointmentDTO replaceHistoryAppointment(@RequestBody HistoryAppointmentDTO newHistoryAppointment, @PathVariable Long id) {
        return historyAppointmentService.updateHistoryAppointment(newHistoryAppointment,id);
    }

    @DeleteMapping("/{id}")
    void deleteHistoryAppointment(@PathVariable Long id) {
        historyAppointmentService.deleteHistoryAppointmentById(id);
    }

}
