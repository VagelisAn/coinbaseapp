package com.server.vet.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="history_appointment")
public class HistoryAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.SEQUENCE
    @SequenceGenerator(name = "id", sequenceName = "id_sequence") //, initialValue = 1
    private Long id;

    private String treatment;

    private String symptoms;

    private String comment;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;



}
