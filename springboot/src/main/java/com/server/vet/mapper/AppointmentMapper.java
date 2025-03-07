package com.server.vet.mapper;

import com.server.vet.dto.AppointmentDTO;
import com.server.vet.dto.ExamDTO;
import com.server.vet.entity.Appointment;
import com.server.vet.entity.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper MAPPER = Mappers.getMapper(AppointmentMapper.class);

    AppointmentDTO mapToAppointmentDTO(Appointment appointment);

    Appointment mapToAppointment(AppointmentDTO appointment);

    List<Appointment> mapToAppointmentDTO(List<AppointmentDTO> list);

    List<AppointmentDTO> mapToAppointment(List<Appointment> list);

}
