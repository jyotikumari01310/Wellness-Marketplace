package com.wellness.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.*;
import com.wellness.backend.repository.*;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final UserRepository userRepo;
    private final PractitionerRepository practitionerRepo;

    public AppointmentService(AppointmentRepository appointmentRepo,
                              UserRepository userRepo,
                              PractitionerRepository practitionerRepo) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
        this.practitionerRepo = practitionerRepo;
    }

    // ✅ BOOK APPOINTMENT
    public Appointment book(String email, Long practitionerId, LocalDateTime time) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PractitionerProfile practitioner = practitionerRepo.findById(practitionerId)
                .orElseThrow(() -> new RuntimeException("Practitioner not found"));

        if (!practitioner.isVerified()) {
            throw new RuntimeException("Practitioner not verified");
        }

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setPractitioner(practitioner);
        appointment.setAppointmentTime(time);
        appointment.setStatus(AppointmentStatus.BOOKED);

        return appointmentRepo.save(appointment);
    }

    // ✅ VIEW MY APPOINTMENTS
    public List<Appointment> myAppointments(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return appointmentRepo.findByUser(user);
    }
}
