package com.server.vet.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_sequence", initialValue = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "post_code")
    private String postcode;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "phone")
    private String phone;

    @Column(name = "keycloak_id")
    private String keycloakId;

//    @OneToOne(mappedBy = "owner")
//    private User user;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL) //fetch = FetchType.LAZY,
    private List<Pet> petList;


//    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL, orphanRemoval = true) //fetch = FetchType.LAZY,
//    private List<Appointment> appointmentList;

}
