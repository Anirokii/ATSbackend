package com.example.ATSproject.Services;

import com.example.ATSproject.Enums.InterviewStatus;
import com.example.ATSproject.Exceptions.JobNotFoundException;
import com.example.ATSproject.Modals.Candidate;
import com.example.ATSproject.Modals.Interview;
import com.example.ATSproject.Repos.InterviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {
    public final InterviewRepo interviewRepo;

    @Autowired
    public InterviewService(InterviewRepo interviewRepo) {
        this.interviewRepo = interviewRepo;
    }

    //list of services

    //add new candidate service
    public Interview addInterview(Interview newInterview){
        return interviewRepo.save(newInterview);
    }

    //find the candidate by ID
    public Interview findInterviewbyId(Long id){
        return interviewRepo.findById(id).
                orElseThrow(() -> new JobNotFoundException("Interview by id "+ id + "was not found"));
    }
    public List<Interview> findAllInterview(){
        return interviewRepo.findAll();
    }

    //Update the candidate
    public Interview updateinterview(Interview interview) {
        return interviewRepo.save(interview);
    }

    //delete a candidate

    public void deleteinterview(Long id){
        interviewRepo.deleteById(id);
    }

    //List of filters

    public List<Interview> sortInterviewsByResult(String sortDirection) {
        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "result");
        return interviewRepo.findAll(sort);
    }

    public List<Interview> sortInterviewsByDate(String sortDirection) {
        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "dateInterview");
        return interviewRepo.findAll(sort);
    }

    public List<Interview> filterInterviewsByStatus(InterviewStatus status) {
        return interviewRepo.findByInterviewStatus(status);
    }

}
