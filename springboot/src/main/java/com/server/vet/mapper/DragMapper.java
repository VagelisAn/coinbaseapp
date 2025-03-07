package com.server.vet.mapper;

import com.server.vet.dto.AppointmentDTO;
import com.server.vet.dto.DragDTO;
import com.server.vet.entity.Appointment;
import com.server.vet.entity.Drag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DragMapper {

    DragDTO mapToDragDTO(Drag drag);

    Drag mapToDrag(DragDTO dragDTO);

    List<Drag> mapToDragDTO(List<DragDTO> list);

    List<DragDTO> mapToDrag(List<Drag> list);

}
