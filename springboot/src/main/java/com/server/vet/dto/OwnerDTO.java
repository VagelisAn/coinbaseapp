package com.server.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerDTO implements Serializable {

    private Long id;
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    private String firstname;
    @NotBlank(message = "Invalid LastName: Empty LastName")
    @NotNull(message = "Invalid Last Name: Last Name is NULL")
    private String lastname;
    private String email;
    private String country;
    private String city;
    private String postcode;
    private String address;
    @NotBlank(message = "Invalid Mobile: Empty Mobile")
    @NotNull(message = "Invalid Mobile: Mobile is NULL")
    private String mobile;
    private String phone;
    private String contact;

    private String keycloakId;

    private List<PetDTO> petDTOList;

    private List<AppointmentDTO> appointmentDTOList;

    public OwnerDTO(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public static void sort(List<OwnerDTO> list) {

        list.sort((o1, o2)
                -> o1.getLastname().compareTo(
                o2.getLastname()));
    }

}
