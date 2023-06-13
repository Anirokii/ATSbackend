package com.example.ATSproject.Controllers;

import com.example.ATSproject.Modals.Job;
import com.example.ATSproject.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobResource {
    public final JobService jobService;
    @Autowired
    public JobResource(JobService jobService) {
        this.jobService = jobService;
    }

    //list of requestes

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job> jobs = jobService.findAllJob();
        return  new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Job> getJobById (@PathVariable("id") Long id){
        Job job = jobService.findJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        Job newJob = jobService.addJob(job);
        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Job> updateJob(@RequestBody Job job) {
        Job updateJob = jobService.updateJob(job);
        return new ResponseEntity<>(updateJob, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable ("id") Long id){
        jobService.deleteJob(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter/experience")
    public ResponseEntity<List<Job>> filterJobsByExperience(@RequestParam("min") int minExperience, @RequestParam("max") int maxExperience) {
        List<Job> filteredJobs = jobService.filterJobsByExperience(minExperience, maxExperience);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }

    @GetMapping("/filter/location")
    public ResponseEntity<List<Job>> filterJobsByLocation(@RequestParam("location") String location) {
        List<Job> filteredJobs = jobService.filterJobsByLocation(location);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }

    @GetMapping("/filter/department")
    public ResponseEntity<List<Job>> filterJobsByDepartment(@RequestParam("department") String department) {
        List<Job> filteredJobs = jobService.filterJobsByDepartment(department);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }
}
