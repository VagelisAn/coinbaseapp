package com.server.vet.service;

import com.server.vet.dto.AppointmentDTO;
import com.server.vet.dto.OwnerDTO;
import com.server.vet.entity.Appointment;

import java.io.IOException;
import java.util.List;

public interface AppointmentService {
    public List<AppointmentDTO> all() throws IOException;

    public AppointmentDTO newAppointment(AppointmentDTO appointmentDTO);

    public AppointmentDTO one(Long id);

    public AppointmentDTO replaceAppointment(AppointmentDTO appointmentDTO, Long id);

    public void deleteAppointment(Long id);

}
