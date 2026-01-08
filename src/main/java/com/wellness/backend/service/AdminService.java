package com.wellness.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.PractitionerProfile;
import com.wellness.backend.repository.PractitionerRepository;

@Service
public class AdminService {

    private final PractitionerRepository practitionerRepo;

    public AdminService(PractitionerRepository practitionerRepo) {
        this.practitionerRepo = practitionerRepo;
    }

    // 1️⃣ View pending applications
    public List<PractitionerProfile> getPendingApplications() {
        return practitionerRepo.findByVerifiedFalse();
    }

    // 2️⃣ Verify practitioner
    public PractitionerProfile verifyPractitioner(Long id) {

        PractitionerProfile profile = practitionerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        profile.setVerified(true);
        return practitionerRepo.save(profile);
    }
}
