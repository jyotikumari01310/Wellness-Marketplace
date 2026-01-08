package com.wellness.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellness.backend.entity.PractitionerProfile;
import com.wellness.backend.entity.User;

public interface PractitionerRepository
        extends JpaRepository<PractitionerProfile, Long> {

    Optional<PractitionerProfile> findByUser(User user);

    List<PractitionerProfile> findByVerifiedFalse();
    
    List<PractitionerProfile> findByVerifiedTrue();

}
