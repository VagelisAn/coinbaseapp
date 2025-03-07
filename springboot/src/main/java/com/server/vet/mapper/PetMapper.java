package com.server.vet.mapper;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.entity.Owner;
import com.server.vet.entity.Pet;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses={
        ExamMapper.class,
        TreatmentMapper.class,
        WeightMapper.class,
        VaccinationMapper.class,
        ReminderMapper.class,
        OwnerMapper.class
        }
)
public interface PetMapper {


    @Named("mapToPet")
    @Mapping(source = "examDTOList", target = "examList")
    @Mapping(source = "treatmentDTOList", target = "treatmentList")
    @Mapping(source = "weightDTOList", target = "weightList")
    @Mapping(source = "historyAppointmentDTOList", target = "historyAppointmentList")
    @Mapping(source = "vaccinationDTOList", target = "vaccinationList")
    @Mapping(source = "reminderDTOList", target = "reminderList")
    Pet mapToPet(PetDTO petDTO);
    @IterableMapping(qualifiedByName="mapToPet")
    List<Pet> mapToPetDTO(List<PetDTO> petDTOList);

    @Named("mapToPetDTO")
    @Mapping(source = "examList", target = "examDTOList")
    @Mapping(source = "treatmentList", target = "treatmentDTOList")
    @Mapping(source = "weightList", target = "weightDTOList")
    @Mapping(source = "historyAppointmentList", target = "historyAppointmentDTOList")
    @Mapping(source = "vaccinationList", target = "vaccinationDTOList")
    @Mapping(source = "reminderList", target = "reminderDTOList")
    @Mapping(source = "owner.id", target = "idOwner")
    @Mapping(source = "owner.firstname", target = "nameOwner")
    @Mapping(source = "owner.mobile", target = "phoneOwner")
    @Mapping(source = "owner.lastname", target = "surnOwner")
    PetDTO mapToPetDTO(Pet pet);


    @IterableMapping(qualifiedByName="mapToPetDTO")
    List<PetDTO> mapToPet(List<Pet> petList);


    @Named("withOutChild")
    @Mapping(target = "examDTOList", ignore = true)
    @Mapping(target = "treatmentDTOList", ignore = true)
    @Mapping(target = "weightDTOList", ignore = true)
    @Mapping(target = "historyAppointmentDTOList", ignore = true)
    @Mapping(target = "vaccinationDTOList", ignore = true)
    @Mapping(target = "reminderDTOList", ignore = true)
    @Mapping(source = "owner.id", target = "idOwner")
    @Mapping(source = "owner.firstname", target = "nameOwner")
    @Mapping(source = "owner.mobile", target = "phoneOwner")
    @Mapping(source = "owner.lastname", target = "surnOwner")
    PetDTO withOutChildDTO(Pet pet);

    @IterableMapping(qualifiedByName="withOutChild")
    List<PetDTO> withOutChildDTOs(List<Pet> pet);

    @Named("withOutChildPet")
    @Mapping(target = "examList", ignore = true)
    @Mapping(target = "treatmentList", ignore = true)
    @Mapping(target = "weightList", ignore = true)
    @Mapping(target = "historyAppointmentList", ignore = true)
    @Mapping(target = "vaccinationList", ignore = true)
    @Mapping(target = "reminderList", ignore = true)
    Pet withOutChildPet(PetDTO petDTO);

    @IterableMapping(qualifiedByName="withOutChildPet")
    List<Pet> withOutChildPets(List<PetDTO> petDTO);

    @Named("onlyBasicInfo")
    @Mapping(target = "examDTOList", ignore = true)
    @Mapping(target = "treatmentDTOList", ignore = true)
    @Mapping(target = "weightDTOList", ignore = true)
    @Mapping(target = "historyAppointmentDTOList", ignore = true)
    @Mapping(target = "vaccinationDTOList", ignore = true)
    @Mapping(target = "reminderDTOList", ignore = true)
    @Mapping(target = "species", ignore = true)
    @Mapping(target = "breed", ignore = true)
    @Mapping(target = "pedigree", ignore = true)
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "sterilized", ignore = true)
    @Mapping(target = "sterilizedComment", ignore = true)
    @Mapping(target = "sterilizationDate", ignore = true)
    @Mapping(target = "diet", ignore = true)
    @Mapping(target = "dietComment", ignore = true)
    @Mapping(target = "liveConditions", ignore = true)
    @Mapping(target = "liveOtherAnimals", ignore = true)
    @Mapping(target = "liveOtherAnimalsComment", ignore = true)
    PetDTO onlyBasicInfoDTO(Pet pet);

    @IterableMapping(qualifiedByName="onlyBasicInfo")
    List<PetDTO> onlyBasicInfoDTOs(List<Pet> pet);

    @Named("onlyBasicInfoPet")
    @Mapping(target = "examList", ignore = true)
    @Mapping(target = "treatmentList", ignore = true)
    @Mapping(target = "weightList", ignore = true)
    @Mapping(target = "historyAppointmentList", ignore = true)
    @Mapping(target = "vaccinationList", ignore = true)
    @Mapping(target = "reminderList", ignore = true)
    @Mapping(target = "species", ignore = true)
    @Mapping(target = "breed", ignore = true)
    @Mapping(target = "pedigree", ignore = true)
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "sterilized", ignore = true)
    @Mapping(target = "sterilizedComment", ignore = true)
    @Mapping(target = "sterilizationDate", ignore = true)
    @Mapping(target = "diet", ignore = true)
    @Mapping(target = "dietComment", ignore = true)
    @Mapping(target = "liveConditions", ignore = true)
    @Mapping(target = "liveOtherAnimals", ignore = true)
    @Mapping(target = "liveOtherAnimalsComment", ignore = true)
    Pet onlyBasicInfoPet(PetDTO petDTO);

    @IterableMapping(qualifiedByName="onlyBasicInfoPet")
    List<Pet> onlyBasicInfoPets(List<PetDTO> petDTO);
}
