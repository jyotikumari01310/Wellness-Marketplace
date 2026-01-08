package com.wellness.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.PractitionerProfile;
import com.wellness.backend.service.PractitionerService;

@RestController
@RequestMapping("/api/practitioners")
public class PractitionerController {

    private final PractitionerService practitionerService;

    public PractitionerController(PractitionerService practitionerService) {
        this.practitionerService = practitionerService;
    }

    // Apply as practitioner
    @PostMapping("/apply")
    public PractitionerProfile apply(
            @RequestParam String email,
            @RequestParam String specialization) {

        return practitionerService.apply(email, specialization);
    }

    // Verify practitioner (admin)
    @PutMapping("/{id}/verify")
    public PractitionerProfile verify(@PathVariable Long id) {
        return practitionerService.verifyPractitioner(id);
    }

    // Get verified practitioners (users)
    @GetMapping
    public List<PractitionerProfile> getVerified() {
        return practitionerService.getVerifiedPractitioners();
    }
}
