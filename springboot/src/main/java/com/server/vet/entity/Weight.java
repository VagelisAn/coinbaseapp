package com.server.vet.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="weight")
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_sequence", initialValue = 1)
    private Long id;
    @Column(name = "weight")
    private double weightValue;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet  pet;

}
