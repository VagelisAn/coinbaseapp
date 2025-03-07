package com.server.vet.mapper;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.entity.Owner;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = {PetMapper.class})
public interface OwnerMapper{

    @Named("mapToOwnerDTO")
    @Mapping(source = "petList", target = "petDTOList", qualifiedByName = "withOutChild")
    @Mapping(source=".", qualifiedByName = "getContuct", target = "contact")
    OwnerDTO mapToOwnerDTO(Owner owner);
    @IterableMapping(qualifiedByName="mapToOwnerDTO")
    List<OwnerDTO> mapToOwnerDTOs(List<Owner> ownerList);

    @Named("mapToOwnerWithBasicPetDTO")
    @Mapping(source = "petList", target = "petDTOList", qualifiedByName = "onlyBasicInfo")
    @Mapping(source=".", qualifiedByName = "getContuct", target = "contact")
    OwnerDTO mapToOwnerWithBasicPetDTO(Owner owner);
    @IterableMapping(qualifiedByName="mapToOwnerWithBasicPetDTO")
    List<OwnerDTO> mapToOwnerWithBasicPetDTOs(List<Owner> ownerList);

    @Named("getContuct")
    default String getContuct(Owner owner) {
        return (owner.getPhone() != null ? ("Σταθερό : " + owner.getPhone()+ "\n") : "")  +
                (owner.getMobile()!= null ? ("Κινητό: " + owner.getMobile()) : "");
    }

    @Named("getOwnerInfo")
    default String getOwnerInfo(Owner owner) {
        return (owner.getFirstname() != null ? ("Όνομα : " + owner.getFirstname()+ "\n") : "")  +
                (owner.getLastname()!= null ? ("Επώνυμο: " + owner.getLastname()) : "");
    }

    @Named("mapToOwner")
    @Mapping(source = "petDTOList", target = "petList", qualifiedByName = "withOutChildPet")
    Owner mapToOwner(OwnerDTO ownerDTO);
    @IterableMapping(qualifiedByName="mapToOwner")
    List<Owner> mapToOwners(List<OwnerDTO> ownerDTOList);

    @Named("mapToOwnerWithBasic")
    @Mapping(source = "petDTOList", target = "petList", qualifiedByName = "onlyBasicInfoPet")
    Owner mapToOwnerWithBasic(OwnerDTO ownerDTO);
    @IterableMapping(qualifiedByName="mapToOwnerWithBasic")
    List<Owner> mapToOwnersWithBasic(List<OwnerDTO> ownerDTOList);

}
