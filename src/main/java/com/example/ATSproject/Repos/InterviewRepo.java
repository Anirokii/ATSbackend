package com.example.ATSproject.Repos;

import com.example.ATSproject.Enums.InterviewStatus;
import com.example.ATSproject.Modals.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepo extends JpaRepository<Interview,Long> {
    List<Interview> findByInterviewStatus(InterviewStatus status);
}
