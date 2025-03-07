package com.server.vet.mapper;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.TreatmentDTO;
import com.server.vet.entity.Owner;
import com.server.vet.entity.Treatment;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", uses = {DragMapper.class})
public interface TreatmentMapper {

    @Named("mapToTreatmentDTO")
    @Mapping(source = "dragList", target = "dragsListDTO")
//    @Mapping(source = "symptoms", target = "symptoms")
    @Mapping(source = "pet.id", target = "petId")
    TreatmentDTO mapToTreatmentDTO(Treatment treatment);

    @IterableMapping(qualifiedByName="mapToTreatmentDTO")
    List<TreatmentDTO> mapToTreatmentDTOs(List<Treatment> list);


    @Named("mapToTreatment")
    @Mapping(source = "dragsListDTO", target = "dragList")
//    @Mapping(source = "symptoms", target = "symptoms")
    Treatment mapToTreatment(TreatmentDTO treatmentDTO);

    @IterableMapping(qualifiedByName="mapToTreatment")
    List<Treatment> mapToTreatments(List<TreatmentDTO> list);


    // MapStruct will know to use this method to map between a `String` and `List<Address>`
//    default String mapListToString(List<String> list) {
//        return list != null ? String.join(", ", list) : null;
//    }
//
//    // Custom method to convert a String to a List<String>
//    default List<String> map(String value) {
//        // Split the string by a delimiter, e.g., comma, and convert to a list
//        return value != null ? Arrays.asList(value.split(",\\s*")) : null;
//    }
}
