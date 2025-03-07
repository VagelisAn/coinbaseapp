package com.server.vet.controller;


import com.server.vet.dto.AppointmentDTO;
import com.server.vet.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/appoint")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public List<AppointmentDTO> all() throws IOException {
        return appointmentService.all();
    }

    @PostMapping()
    public AppointmentDTO newAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.newAppointment(appointmentDTO);
    }


    @GetMapping("/{id}")
    public AppointmentDTO one(@PathVariable Long id) {
        return appointmentService.one(id);
    }

    @PutMapping("/{id}")
    public AppointmentDTO replaceAppointment(@RequestBody AppointmentDTO appointmentDTO, @PathVariable Long id) {
        return appointmentService.replaceAppointment(appointmentDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

}
