package com.example.ATSproject.Services;

import com.example.ATSproject.Exceptions.JobNotFoundException;
import com.example.ATSproject.Modals.Job;
import com.example.ATSproject.Modals.SearchResult;
import com.example.ATSproject.Modals.Technology;
import com.example.ATSproject.Repos.JobRepo;
import com.example.ATSproject.Repos.SearchResultRepo;
import com.example.ATSproject.Repos.TechnologyRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    public final JobRepo jobRepo;
    private final SearchResultRepo searchResultRepo;
    private final TechnologyRepo technologyRepo;

    @Autowired
    public JobService(JobRepo jobRepo, SearchResultRepo searchResultRepo, TechnologyRepo technologyRepo) {
        this.jobRepo = jobRepo;
        this.searchResultRepo = searchResultRepo;
        this.technologyRepo = technologyRepo;
    }

    //list of services

    //add new job service
    public Job addJob(Job newJob){
        return jobRepo.save(newJob);
    }

    //find the job by ID
    public Job findJobById(Long id){
        return jobRepo.findById(id).
                orElseThrow(() -> new JobNotFoundException("User by id "+ id + "was not found"));
    }
    public List<Job> findAllJob(){
        return jobRepo.findAll();
    }

    //Update the job
    public Job updateJob(Job job) {
        return jobRepo.save(job);
    }

    //delete a job

//    public void deleteJob(Long id){
//        jobRepo.deleteById(id);
//    }

    @Transactional
    public void deleteJob(Long jobId) {
        Optional<Job> jobOptional = jobRepo.findById(jobId);
        Job job = jobOptional.get();

        // Delete search results for this job
        List<SearchResult> searchResults = searchResultRepo.findByJobId(jobId);
        for (SearchResult searchResult : searchResults) {
            searchResultRepo.delete(searchResult);
        }

        // Delete job
        jobRepo.delete(job);
    }

    //list of filters
    public List<Job> filterJobsByExperience(int minExperience, int maxExperience) {
        return jobRepo.findByExperienceRequiredBetween(minExperience, maxExperience);
    }

    public List<Job> filterJobsByLocation(String location) {
        return jobRepo.findByLocation(location);
    }

    public List<Job> filterJobsByDepartment(String department) {
        return jobRepo.findByDepartment(department);
    }



}
