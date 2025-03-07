package com.server.vet.mapper;

import com.server.vet.dto.ExamDTO;
import com.server.vet.dto.VaccinationDTO;
import com.server.vet.entity.Exam;
import com.server.vet.entity.Vaccination;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VaccinationMapper {

    @Named("mapToVaccinationDTO")
    @Mapping(source = "pet.id", target = "petId")
    VaccinationDTO mapToVaccinationDTO(Vaccination vaccination);
    @IterableMapping(qualifiedByName="mapToVaccinationDTO")
    List<VaccinationDTO> mapToVaccinationDTOs(List<Vaccination> list);


    @Named("mapToVaccination")
    Vaccination mapToVaccination(VaccinationDTO vaccinationDTO);
    @IterableMapping(qualifiedByName="mapToVaccination")
    List<Vaccination> mapToVaccinations(List<VaccinationDTO> list);


}
