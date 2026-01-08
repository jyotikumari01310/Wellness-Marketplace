package com.wellness.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellness.backend.entity.Appointment;
import com.wellness.backend.entity.PractitionerProfile;
import com.wellness.backend.entity.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUser(User user);

    List<Appointment> findByPractitioner(PractitionerProfile practitioner);
}
