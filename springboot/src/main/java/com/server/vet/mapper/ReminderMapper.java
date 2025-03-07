package com.server.vet.mapper;

import com.server.vet.dto.ExamDTO;
import com.server.vet.dto.ReminderDTO;
import com.server.vet.dto.ReminderForSendingSMSDTO;
import com.server.vet.entity.Exam;
import com.server.vet.entity.Owner;
import com.server.vet.entity.Reminder;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    @Named("mapToReminderDTO")
    @Mapping(source = "pet.name", target = "petName")
    @Mapping(source = "pet.microchip", target = "petChip")
    @Mapping(source = "pet.species", target = "species")
    @Mapping(source = "pet.id", target = "petId")
    @Mapping(source = "pet.owner.id", target = "ownerId")
    @Mapping(source=".", qualifiedByName = "getFullName", target = "owner")
    @Mapping(source=".", qualifiedByName = "getContactInfo", target = "ownerContact")
    ReminderDTO mapToReminderDTO(Reminder reminder);

    @IterableMapping(qualifiedByName="mapToReminderDTO")
    List<ReminderDTO> mapToReminderDTOs(List<Reminder> list);

    @Named("getFullName")
    default String getFullName(Reminder reminder) {
        if(reminder.getPet() != null && reminder.getPet().getOwner() != null)
            return reminder.getPet().getOwner().getFirstname() + "\n" + reminder.getPet().getOwner().getLastname();
        return "";
    }

    @Named("getContactInfo")
    default String getContactInfo(Reminder reminder) {
        if(reminder.getPet() != null && reminder.getPet().getOwner() != null)
            return reminder.getPet().getOwner().getPhone() != null ? ("Σταθερό : " + reminder.getPet().getOwner().getPhone()+ "\n") : ""  +
                    reminder.getPet().getOwner().getMobile()!= null ? ("Κινητό: " + reminder.getPet().getOwner().getMobile()) : "";
        return "";
    }
    @Named("mapToReminder")
    Reminder mapToReminder(ReminderDTO reminderDTO);

    @IterableMapping(qualifiedByName="mapToReminder")
    List<Reminder> mapToReminders(List<ReminderDTO> list);

    @Named("mapToReminderForSendingDTO")
    @Mapping(source=".", qualifiedByName = "getMessageForSending", target = "message")
    @Mapping(source=".", qualifiedByName = "getFullNameForSending", target = "header")
    @Mapping(source=".", qualifiedByName = "getMobileForSending", target = "mobileNumber")
    ReminderForSendingSMSDTO mapToReminderForSendingDTO(Reminder reminder);

    @IterableMapping(qualifiedByName="mapToReminderForSendingDTO")
    List<ReminderForSendingSMSDTO> mapToReminderForSendingDTOs(List<Reminder> list);

    @Named("getFullNameForSending")
    default String getFullNameForSending(Reminder reminder) {
        if(reminder.getPet() != null && reminder.getPet().getOwner() != null)
            return reminder.getPet().getOwner().getFirstname().toUpperCase() + "  " + reminder.getPet().getOwner().getLastname().toUpperCase()+ " Υπενθημηση";
        return "";
    }
    @Named("getMobileForSending")
    default String getMobileForSending(Reminder reminder) {
        if(reminder.getPet() != null && reminder.getPet().getOwner() != null)
            return reminder.getPet().getOwner().getMobile()!= null ?  reminder.getPet().getOwner().getMobile() : "";
        return "";
    }

    @Named("getMessageForSending")
    default String getMessageForSending(Reminder reminder) {
        if(reminder.getPet() != null)
            return "Το κατικοιδιο σας με ονομα " +reminder.getPet().getName()
                    + "\n και αριθμο τσιπ " +
                    reminder.getPet().getMicrochip()
                    + "\n Πρέπει στης " +
                    reminder.getDate()
                    + "\n Για τον ακολουθω λόγο/υς " +
                    reminder.getComment();
        return "";
    }
}
