package com.server.vet.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;


import java.sql.Types;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="examines")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_sequence", initialValue = 1)
    private Long id;

    private String name;

    private String contentType;

    private String typeExams;

    private Long size;

    private String extension;

    private String storeUrl;

//    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;

    @Lob
    @JdbcTypeCode(Types.VARBINARY)
    private byte[] data;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;



}
