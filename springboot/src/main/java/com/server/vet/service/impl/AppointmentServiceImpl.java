package com.server.vet.service.impl;

import com.server.vet.dto.AppointmentDTO;
import com.server.vet.entity.Appointment;
import com.server.vet.entity.Owner;
import com.server.vet.entity.Pet;
import com.server.vet.mapper.AppointmentMapper;
import com.server.vet.repository.AppointmentRepository;
import com.server.vet.repository.OwnerRepository;
import com.server.vet.repository.PetRepository;
import com.server.vet.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {


    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final AppointmentRepository appointmentRepository;

    public List<AppointmentDTO> all() throws IOException {
        List<AppointmentDTO> appointmentListReturn = new ArrayList<>();
        List<Appointment> list = appointmentRepository.findAll();
        if(!list.isEmpty()) {
            for (Appointment appointment : list
            ) {

                Optional<Owner> owner = ownerRepository.findById(appointment.getOwnerId());
                Optional<Pet> pet = petRepository.findById(appointment.getPetId());
                AppointmentDTO appointmentDTO = AppointmentMapper.MAPPER.mapToAppointmentDTO(appointment);
                appointmentDTO.setOwnerName(owner.get().getFirstname().concat(" ").concat(owner.get().getLastname()));
                appointmentDTO.setPhone(owner.get().getPhone());
                appointmentDTO.setMicrochip(pet.get().getMicrochip());
                appointmentDTO.setPetName(pet.get().getName());
                String date = appointmentDTO.getDate().toString();
//            String time = appointmentDTO.getTime().toString().split(" ")[1];
                appointmentDTO.setCompareDate(LocalDateTime.parse(date.concat("T").concat(appointment.getTime())));
                appointmentListReturn.add(appointmentDTO);
            }
        }
        appointmentListReturn.sort( Comparator.naturalOrder() ) ;
        return appointmentListReturn;
    }

    public AppointmentDTO newAppointment(AppointmentDTO appointmentDTO) {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        if(appointmentList.size() > 0) {
            for (Appointment appoint : appointmentList
            ) {
                if (appoint.getDate().compareTo(appointmentDTO.getDate()) == 0 && appoint.getTime() == appointmentDTO.getTime()) {
                    throw new RuntimeException("Date and time is booked");
                }
            }
        }
        Appointment appointment = AppointmentMapper.MAPPER.mapToAppointment(appointmentDTO);
        return AppointmentMapper.MAPPER.mapToAppointmentDTO(appointmentRepository.save(appointment));
    }

    public AppointmentDTO one(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isPresent()){
            return AppointmentMapper.MAPPER.mapToAppointmentDTO(appointment.get());
        }
        return null;
    }

    @Override
    public AppointmentDTO replaceAppointment(AppointmentDTO appointmentDTO, Long id) {
        return null;
    }

//    public AppointmentDTO replaceOwner(AppointmentDTO newOwnerDTO, Long id) {
//
//        return ownerRepository.findById(id)
//                .map(owner -> {
//                    Owner newOwner = OwnerMapper.MAPPER.mapToOwner(newOwnerDTO);
//                    newOwner.setId(owner.getId());
//                    List<Pet> pets = newOwner.getPetList();
//                    newOwner.setPetList(null);
//                    List<Pet> newPets = new ArrayList<>();
//                    for (Pet pet:pets
//                         ) {
//                        pet.setOwner(newOwner);
//                        newPets.add(petRepository.save(pet));
//                    }
//                    newOwner.setPetList(newPets);
//                    Owner owner1 = ownerRepository.save(newOwner);
//                    return OwnerMapper.MAPPER.mapToOwnerDTO(owner1);
//                })
//                .orElseGet(() -> {
//                   Owner owner = ownerRepository.save(OwnerMapper.MAPPER.mapToOwner(newOwnerDTO));
//                    return OwnerMapper.MAPPER.mapToOwnerDTO(owner);
//                });
//    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

}
