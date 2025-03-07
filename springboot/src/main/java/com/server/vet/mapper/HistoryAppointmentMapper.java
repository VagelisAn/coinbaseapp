package com.server.vet.mapper;


import com.server.vet.dto.HistoryAppointmentDTO;
import com.server.vet.entity.HistoryAppointment;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryAppointmentMapper {

    @Named("mapToHistoryAppointmentDTO")
    @Mapping(source = "pet.id", target = "petId")
    HistoryAppointmentDTO mapToHistoryAppointmentDTO(HistoryAppointment historyAppointment);
    @IterableMapping(qualifiedByName="mapToHistoryAppointmentDTO")
    List<HistoryAppointmentDTO> mapToHistoryAppointmentDTOs(List<HistoryAppointment> list);

    @Named("mapToHistoryAppointment")
    HistoryAppointment mapToHistoryAppointment(HistoryAppointmentDTO historyAppointmentDTO);
    @IterableMapping(qualifiedByName="mapToHistoryAppointment")
    List<HistoryAppointment> mapToHistoryAppointments(List<HistoryAppointmentDTO> list);


}
