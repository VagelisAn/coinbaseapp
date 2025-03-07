package com.server.vet.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vaccination")
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.SEQUENCE
    @SequenceGenerator(name = "id", sequenceName = "id_sequence") // initialValue = 1
    private Long id;

    private String name;

    private String reminder;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date repeatDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;



}
