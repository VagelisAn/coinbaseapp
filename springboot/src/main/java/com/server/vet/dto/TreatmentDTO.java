package com.server.vet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreatmentDTO implements Serializable {


    private Long id;
//    private List<String> symptoms;
    private String symptoms1;
    private String symptoms2;
    private String comment;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

//    private boolean isEditMode = false;
//
//    private PetDTO petDTO;
    private Long petId;

    private List<DragDTO> dragsListDTO;


}
