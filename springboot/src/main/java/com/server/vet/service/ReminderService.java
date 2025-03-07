package com.server.vet.service;



import com.server.vet.dto.ReminderDTO;
import com.server.vet.dto.ReminderForSendingSMSDTO;
import com.server.vet.dto.TreatmentDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ReminderService {
    List<ReminderDTO> getAllRemindersByPetId(Long id) throws IOException;

    ReminderDTO createReminder(ReminderDTO newReminder);

    ReminderDTO getReminderById(Long id);

    ReminderDTO updateReminder(ReminderDTO newReminder, Long id);

    void deleteReminder(Long id);

    List<ReminderDTO> getAllReminders() throws IOException;

    List<ReminderDTO> getTodayReminders() throws IOException;

    List<ReminderForSendingSMSDTO> getForSendingReminders() throws IOException;

    List<ReminderDTO> getRemindersByDateRange(String start, String end) throws IOException;
}
