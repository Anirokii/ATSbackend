package com.example.ATSproject.Repos;

import com.example.ATSproject.Enums.CandidateStatus;
import com.example.ATSproject.Modals.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    List<Candidate> findByExperienceLevel(int experienceLevel);

    List<Candidate> findByStatus(CandidateStatus status);
}
