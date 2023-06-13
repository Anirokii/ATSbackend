package com.example.ATSproject.Controllers;

import com.example.ATSproject.Modals.Interview;
import com.example.ATSproject.Services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewResource {
    public final InterviewService interviewService;
    @Autowired
    public InterviewResource(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    //List of requestes

    @GetMapping("/all")
    public ResponseEntity<List<Interview>> getAllInterviews (){
        List<Interview> interviews = interviewService.findAllInterview();
        return  new ResponseEntity<>(interviews, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Interview> getInterviewById (@PathVariable("id") Long id){
        Interview interview = interviewService.findInterviewbyId(id);
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Interview> addInterview(@RequestBody Interview interview){
        Interview newInterview = interviewService.addInterview(interview);
        return new ResponseEntity<>(newInterview, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Interview> updateInterview(@RequestBody Interview interview) {
        Interview updateInterview = interviewService.updateinterview(interview);
        return new ResponseEntity<>(updateInterview, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInterview(@PathVariable ("id") Long id){
        interviewService.deleteinterview(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

}
