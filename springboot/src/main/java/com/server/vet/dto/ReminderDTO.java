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
public class ReminderDTO implements Serializable {

    private Long id;
    private String comment;

    @NotBlank(message = "Invalid Date: Empty Date")
    @NotNull(message = "Invalid Date: Date is NULL")
//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

    private String petName;
    private String petChip;
    private String owner;
    private String ownerContact;
    private Long ownerId;
    private Long petId;

    private String species;

    private PetDTO petDTO;

}
