package com.server.vet.service;



import com.server.vet.dto.HistoryAppointmentDTO;
import com.server.vet.dto.TreatmentDTO;

import java.io.IOException;
import java.util.List;

public interface HistoryAppointmentService {
    List<HistoryAppointmentDTO> getAllByPetId(Long id) throws IOException;

    HistoryAppointmentDTO createHistoryAppointment(HistoryAppointmentDTO newHistoryAppointment);

    HistoryAppointmentDTO getHistoryAppointmentById(Long id);

    HistoryAppointmentDTO updateHistoryAppointment(HistoryAppointmentDTO newHistoryAppointment, Long id);

    void deleteHistoryAppointmentById(Long id);
}
