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
public class WeightDTO implements Serializable {

    private Long id;

    private double weightValue;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

    private PetDTO petDTO;

    private Long  petId;

}
