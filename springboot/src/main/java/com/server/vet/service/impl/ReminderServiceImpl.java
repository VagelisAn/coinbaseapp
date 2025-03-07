package com.server.vet.service.impl;


import com.server.vet.dto.PetDTO;
import com.server.vet.dto.ReminderDTO;
import com.server.vet.dto.ReminderForSendingSMSDTO;
import com.server.vet.entity.Owner;
import com.server.vet.entity.Pet;
import com.server.vet.entity.Reminder;
import com.server.vet.exception.ResourceNotFoundException;
import com.server.vet.mapper.ReminderMapper;
import com.server.vet.repository.ReminderRepository;
import com.server.vet.service.OwnerService;
import com.server.vet.service.PetService;
import com.server.vet.service.ReminderService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class ReminderServiceImpl implements ReminderService {

    private static final String IS_EMPTY = "Empty";
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    private final PetService petService;
    private final OwnerService ownerService;

    private final Date today;

    public ReminderServiceImpl(
            ReminderRepository reminderRepository,
            ReminderMapper reminderMapper,
            PetService petService,
            OwnerService ownerService
    ) {
        this.reminderRepository = reminderRepository;
        this.reminderMapper = reminderMapper;
        this.petService = petService;
        this.ownerService = ownerService;
        this.today = new Date();
    }

    @Override
    public List<ReminderDTO> getAllRemindersByPetId(Long petId) {
        return reminderMapper.mapToReminderDTOs(reminderRepository.findByPetId(petId));
    }

    @Override
    public ReminderDTO createReminder(ReminderDTO reminderDTO) {

        Pet pet = petService.getPetById(reminderDTO.getPetId());

        Owner owner = ownerService.getOwnerByPetId(reminderDTO.getPetId());

        Reminder reminder = reminderMapper.mapToReminder(reminderDTO);
        reminder.setPet(pet);
        Reminder savedReminder = reminderRepository.save(reminder);
        ReminderDTO result = reminderMapper.mapToReminderDTO(savedReminder);
        result.setOwnerContact(owner.getMobile());
        result.setOwner(getContactInfo(owner));
        return result;
    }

    @Override
    public ReminderDTO getReminderById(Long id) {
        return reminderRepository.findById(id)
                .map(reminderMapper::mapToReminderDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found for ID: " + id));
    }

    @Override
    public ReminderDTO updateReminder(ReminderDTO reminderDTO, Long id) {
        return reminderRepository.findById(id)
                .map(existingReminder -> {
                    Reminder updatedReminder = reminderMapper.mapToReminder(reminderDTO);
                    updatedReminder.setId(existingReminder.getId());
                    Reminder savedReminder = reminderRepository.save(updatedReminder);
                    return reminderMapper.mapToReminderDTO(savedReminder);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found for ID: " + id));
    }

    @Override
    public void deleteReminder(Long id) {
        if (!reminderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reminder not found for ID: " + id);
        }
        reminderRepository.deleteById(id);
    }

    @Override
    public List<ReminderDTO> getAllReminders() {
        return reminderMapper.mapToReminderDTOs(reminderRepository.findAll());
    }

    @Override
    public List<ReminderDTO> getTodayReminders() {
        Calendar calendar = resetCalendarTime(today);

        List<Reminder> todayReminders = reminderRepository.findAll().stream()
                .filter(reminder -> reminder.getDate() != null)
                .filter(reminder -> resetCalendarTime(reminder.getDate()).equals(calendar))
                .toList();

        return reminderMapper.mapToReminderDTOs(todayReminders);
    }

    @Override
    public List<ReminderForSendingSMSDTO> getForSendingReminders() throws IOException {
        Calendar calendar = resetCalendarTime(today);

        List<Reminder> todayReminders = reminderRepository.findAll().stream()
                .filter(reminder -> reminder.getDate() != null)
                .filter(reminder -> resetCalendarTime(reminder.getDate()).equals(calendar))
                .toList();


        return reminderMapper.mapToReminderForSendingDTOs(todayReminders);
    }

    @Override
    public List<ReminderDTO> getRemindersByDateRange(String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ROOT);
        List<Reminder> allReminders = reminderRepository.findAll();

        LocalDate startDate = IS_EMPTY.equals(start) ? null : LocalDate.parse(start, formatter);
        LocalDate endDate = IS_EMPTY.equals(end) ? null : LocalDate.parse(end, formatter);

        List<Reminder> filteredReminders = allReminders.stream()
                .filter(reminder -> reminder.getDate() != null)
                .filter(reminder -> isWithinDateRange(getDateToLocalDate(reminder.getDate()), startDate, endDate))
                .toList();

        return reminderMapper.mapToReminderDTOs(filteredReminders);
    }

    private static Calendar resetCalendarTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private static LocalDate getDateToLocalDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static boolean isWithinDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        boolean afterStart = startDate == null || !date.isBefore(startDate);
        boolean beforeEnd = endDate == null || !date.isAfter(endDate);
        return afterStart && beforeEnd;
    }

    private static String getContactInfo(PetDTO pet) {
        return (pet.getNameOwner() != null ? "Name: " + pet.getNameOwner() + "\n" : "") +
                (pet.getSurnOwner() != null ? "Surname: " + pet.getSurnOwner() : "");
    }

    private static String getContactInfo(Owner owner) {
        return (owner.getFirstname() != null ? "Name: " + owner.getFirstname() + "\n" : "") +
                (owner.getLastname() != null ? "Surname: " + owner.getLastname() : "");
    }
}
