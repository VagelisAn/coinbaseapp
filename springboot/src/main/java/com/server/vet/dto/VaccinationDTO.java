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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaccinationDTO implements Serializable {

    private Long id;

    private String name;

    private String reminder;


//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

//    @NotBlank(message = "Invalid Reaped Date: Empty Reaped Date")
//    @NotNull(message = "Invalid Reaped Date: Reaped Date is NULL")
//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date repeatDate;

    private Long petId;

    private PetDTO petDTO;



}
