package com.server.vet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DragDTO implements Serializable {

    private Long id;

    private String dragName;
//    private String comment;
//    private String symptoms;
    private String doses;

    private TreatmentDTO treatmentDTO;

}
