package com.example.ATSproject.Repos;

import com.example.ATSproject.Modals.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepo extends JpaRepository<Job,Long> {
    List<Job> findByExperienceRequiredBetween(int minExperience, int maxExperience);

    List<Job> findByLocation(String location);

    List<Job> findByDepartment(String department);
}
