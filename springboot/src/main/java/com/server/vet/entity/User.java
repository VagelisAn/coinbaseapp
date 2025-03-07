package com.server.vet.entity;


import jakarta.persistence.*;
import lombok.*;

/**
 * @author Greece
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(name = "id", sequenceName = "id_sequence", initialValue = 1)
        private Long id;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "owner_id", referencedColumnName = "id")
        private Owner owner;

}
