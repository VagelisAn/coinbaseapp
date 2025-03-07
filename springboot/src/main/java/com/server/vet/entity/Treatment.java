package com.server.vet.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="treatment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_sequence", initialValue = 1)
    private Long id;

    private String symptoms;
    private String symptoms1;
    private String symptoms2;
    private String comment;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @OneToMany(mappedBy = "treatment", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drag> dragList;



}
