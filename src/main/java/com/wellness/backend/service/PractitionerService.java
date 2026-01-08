package com.wellness.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.wellness.backend.entity.PractitionerProfile;
import com.wellness.backend.entity.User;
import com.wellness.backend.repository.PractitionerRepository;
import com.wellness.backend.repository.UserRepository;

@Service
public class PractitionerService {

    private final PractitionerRepository practitionerRepo;
    private final UserRepository userRepo;

    public PractitionerService(PractitionerRepository practitionerRepo,
                               UserRepository userRepo) {
        this.practitionerRepo = practitionerRepo;
        this.userRepo = userRepo;
    }

    public PractitionerProfile apply(String email, String specialization) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // prevent duplicate application
        if (practitionerRepo.findByUser(user).isPresent()) {
            throw new RuntimeException("Already applied");
        }

        PractitionerProfile p = new PractitionerProfile();
        p.setUser(user);
        p.setSpecialization(specialization);
        p.setVerified(false);

        return practitionerRepo.save(p);
    }

    
    public PractitionerProfile verifyPractitioner(Long practitionerId) {

        PractitionerProfile practitioner = practitionerRepo.findById(practitionerId)
                .orElseThrow(() -> new RuntimeException("Practitioner not found"));

        practitioner.setVerified(true);

        return practitionerRepo.save(practitioner);
    }
    
    public List<PractitionerProfile> getVerifiedPractitioners() {
        return practitionerRepo.findByVerifiedTrue();
    }


}
