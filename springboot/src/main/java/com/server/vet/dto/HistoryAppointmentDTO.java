package com.server.vet.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
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
public class HistoryAppointmentDTO implements Serializable {


    private Long id;

    private String treatment;

    private String symptoms;

    private String comment;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

    private Long petId;
    private PetDTO petDTO;



}
