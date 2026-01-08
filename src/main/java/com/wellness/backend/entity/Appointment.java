package com.wellness.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Patient who booked appointment
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Practitioner with whom appointment is booked
    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private PractitionerProfile practitioner;

    // Date & time of appointment
    private LocalDateTime appointmentTime;

    // Appointment status
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    // ---------- GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PractitionerProfile getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(PractitionerProfile practitioner) {
        this.practitioner = practitioner;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
