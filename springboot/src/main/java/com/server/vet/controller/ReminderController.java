package com.server.vet.controller;


import com.server.vet.dto.ReminderDTO;
import com.server.vet.dto.ReminderForSendingSMSDTO;
import com.server.vet.service.impl.ReminderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/reminder")
public class ReminderController {

    private final ReminderServiceImpl reminderService;

    @GetMapping("/all/{id}")
    List<ReminderDTO> all(@PathVariable Long id){
        return reminderService.getAllRemindersByPetId(id);
    }

    @PostMapping
    ReminderDTO newReminder(@RequestBody ReminderDTO newReminder) {
        return reminderService.createReminder(newReminder);
    }

    @GetMapping("/{id}")
    ReminderDTO one(@PathVariable Long id) {
        return reminderService.getReminderById(id);
    }

    @PutMapping("/{id}")
    ReminderDTO replaceReminder(@RequestBody ReminderDTO newReminder, @PathVariable Long id) {
        return reminderService.updateReminder(newReminder,id);
    }

    @DeleteMapping("/{id}")
    void deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
    }

    @GetMapping("/all")
    List<ReminderDTO> allReminders(){
        return reminderService.getAllReminders();
    }

    @GetMapping("/today")
    List<ReminderDTO> todayReminders(){
        return reminderService.getTodayReminders();
    }

    @GetMapping("/date/{starDate}/{endDate}")
    List<ReminderDTO> reminderBaseDate(@PathVariable("starDate") String starDate, @PathVariable("endDate") String endDate) throws IOException {
        return reminderService.getRemindersByDateRange(starDate,endDate);
    }

    @GetMapping("/for-sending")
    List<ReminderForSendingSMSDTO> forSendingReminders() throws IOException {
        return reminderService.getForSendingReminders();
    }

}
