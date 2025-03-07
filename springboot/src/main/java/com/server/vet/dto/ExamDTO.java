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
public class ExamDTO implements Serializable {


    private Long id;
    private String name;
    private String typeExams;
    private String contentType;
    private Long size;
    private byte[] data;
    private String extension;
    private String url;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

    private Long petId;
    private PetDTO petDTO;


}
