package com.wellness.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.PractitionerProfile;
import com.wellness.backend.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 1️⃣ View all pending applications
    @GetMapping("/practitioners")
    public List<PractitionerProfile> getPending() {
        return adminService.getPendingApplications();
    }

    // 2️⃣ Approve practitioner
    @PutMapping("/practitioners/{id}/verify")
    public PractitionerProfile verify(@PathVariable Long id) {
        return adminService.verifyPractitioner(id);
    }
}
