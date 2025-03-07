package com.server.vet.mapper;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.WeightDTO;
import com.server.vet.entity.Owner;
import com.server.vet.entity.Weight;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeightMapper {

    @Named("mapToWeightDTO")
    @Mapping(target = "petId", source = "pet.id")
    WeightDTO mapToWeightDTO(Weight weight);

    @IterableMapping(qualifiedByName="mapToWeightDTO")
    List<WeightDTO> mapToWeightDTOs(List<Weight> list);

    @Named("mapToWeight")
    Weight mapToWeight(WeightDTO weightDTO);
    @IterableMapping(qualifiedByName="mapToWeight")
    List<Weight> mapToWeights(List<WeightDTO> list);

}
