package com.server.vet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDTO implements Serializable {

    private Long id;
    private String name;
    private String microchip;
    @NotBlank(message = "Invalid Species: Empty species")
    @NotNull(message = "Invalid Species: Species is NULL")
    private String species;
    private String breed;
    private String pedigree;
    private String color;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBirth;
    private char gender; /* 0 = Not known 1 = Mal 2 = Female */
    private Integer sterilized; /* 0 = Not known 1 = yes 2 = no,*/
    private String sterilizedComment;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date sterilizationDate;
    private int diet; /* 0 = viom. konserva CP 1 = biom trofh DF 2 = trofh gia poth xrhh HMO,*/
    private String dietComment;
    private int liveConditions; /* 0 = intor 1 = outdoor 2 = indoor and outdoor */
    private boolean liveOtherAnimals;
    private String liveOtherAnimalsComment;

    private Long idOwner;
    private String nameOwner;
    private String surnOwner;
    private String phoneOwner;
    private String ownerInfo;

    private OwnerDTO ownerDTO;
    private Set<ExamDTO> examDTOList;
    private Set<TreatmentDTO> treatmentDTOList;
    private Set<WeightDTO> weightDTOList;
    private Set<HistoryAppointmentDTO> historyAppointmentDTOList;
    private Set<ReminderDTO> reminderDTOList;
    private Set<VaccinationDTO> vaccinationDTOList;


}
