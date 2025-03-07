package com.server.vet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_sequence", initialValue = 1)
    private Long id;

    @Column(name = "name_animal")
    private String name;

    @Column(name = "microchip")
    private String microchip;

    @Column(name = "species")
    private String species;

    @Column(name = "breed")
    private String breed;

    @Column(name = "pedigree")
    private String pedigree;

    @Column(name = "color")
    private String color;
//    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "date_of_birth")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Column(name = "gender")
    private char gender; /* 0 = Not known 1 = Mal 2 = Female */

    @Column(name = "sterilized")
    private Integer sterilized; /* 0 = Not known 1 = yes 2 = no,*/

    @Column(name = "sterilized_comment")
    private String sterilizedComment;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "sterilized_date")
    private Date sterilizationDate;

    @Column(name = "diet")
    private int diet; /* 0 = viom. konserva CP 1 = biom trofh DF 2 = trofh gia poth xrhh HMO,*/

    @Column(name = "diet_comment")
    private String dietComment;

    @Column(name = "live_conditions")
    private int liveConditions; /* 0 = intor 1 = outdoor 2 = indoor and outdoor */

    @Column(name = "live_other_animals")
    private boolean liveOtherAnimals;

    @Column(name = "live_other_animals_comment")
    private String liveOtherAnimalsComment;

    @ManyToOne(fetch = FetchType.LAZY,optional = true,
            cascade={CascadeType.PERSIST,CascadeType.DETACH}) //CascadeType.MERGE,
    @JoinColumn(name = "owner_id", nullable=true)
    private Owner owner;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Exam> examList;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Treatment> treatmentList;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Weight> weightList;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoryAppointment> historyAppointmentList;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vaccination> vaccinationList;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reminder> reminderList;

}
