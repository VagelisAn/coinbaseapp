package com.server.vet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="drag")
public class Drag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_sequence", initialValue = 1)
    private Long id;
    @Column(name = "drag")
    private String dragName;
//    private String comment;
//    private String symptoms;
    private String doses;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id") //nullable = false
    private Treatment treatment;
}
