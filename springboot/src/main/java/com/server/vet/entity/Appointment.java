package com.server.vet.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




/**
 * @author Greece
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_sequence", initialValue = 1)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;


    @Column(name = "pet_id")
    private Long petId;


    @Column(name = "date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private java.sql.Date date;


    @Column(name = "time")
    private String time;

}
