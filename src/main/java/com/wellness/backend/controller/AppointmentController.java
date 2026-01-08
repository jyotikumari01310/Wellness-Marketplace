package com.wellness.backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.Appointment;
import com.wellness.backend.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Book appointment
    @PostMapping("/book")
    public Appointment book(
            @RequestParam String email,
            @RequestParam Long practitionerId,
            @RequestParam String time) {

        return appointmentService.book(
                email,
                practitionerId,
                LocalDateTime.parse(time));
    }

    // View my appointments
    @GetMapping("/my")
    public List<Appointment> my(@RequestParam String email) {
        return appointmentService.myAppointments(email);
    }
}
