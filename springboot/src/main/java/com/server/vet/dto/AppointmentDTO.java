package com.server.vet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO implements Comparable<AppointmentDTO>, Serializable {

    private Long id;
    private Long ownerId;
    private Long petId;
    private Date date;
    @JsonFormat(pattern = "HH:mm:ss")
    private String time;
    private String ownerName = null;
    private String phone = null;
    private String petName = null;
    private String microchip = null;
    private LocalDateTime compareDate  = null;


    @Override
    public int compareTo(AppointmentDTO o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }
}
