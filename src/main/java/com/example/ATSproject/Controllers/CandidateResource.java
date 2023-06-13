package com.example.ATSproject.Controllers;

import com.example.ATSproject.Enums.CandidateStatus;
import com.example.ATSproject.Modals.Candidate;
import com.example.ATSproject.Services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Candidate")
public class CandidateResource {
    public final CandidateService candidateService;

    @Autowired
    public CandidateResource(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    //List of requestes

    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.findAllcandidate();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Long id) {
        Candidate candidate = candidateService.findCandidatebyId(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidat) {
        Candidate newCandidate = candidateService.addcandidate(candidat);
        return new ResponseEntity<>(newCandidate, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/profile-image")
    public ResponseEntity<String> uploadProfileImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            // Get the person entity by ID
            Candidate candidate = candidateService.findCandidatebyId(id);

            // Set the profile image to the byte array of the uploaded file
            candidate.setImgProfil(file.getBytes());

            // Save the updated person entity
            candidateService.updateCandidate(candidate);

            return ResponseEntity.ok("Profile image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile image");
        }
    }



    @PutMapping("/update")
    public ResponseEntity<Candidate> updateCandidat(@RequestBody Candidate candidate) {
        Candidate updateCandidate = candidateService.updateCandidate(candidate);
        return new ResponseEntity<>(updateCandidate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable ("id") Long id){
        candidateService.deleteCandidate(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sort/score")
    public ResponseEntity<List<Candidate>> sortByScore(@RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder) {
        List<Candidate> sortedCandidates = candidateService.sortByScore(sortOrder);
        return new ResponseEntity<>(sortedCandidates, HttpStatus.OK);
    }

    @GetMapping("/filter/experiencelevel/{level}")
    public ResponseEntity<List<Candidate>> filterCandidatesByExperienceLevel(@PathVariable("level") int experienceLevel) {
        List<Candidate> filteredCandidates = candidateService.filterCandidatesByExperienceLevel(experienceLevel);
        return new ResponseEntity<>(filteredCandidates, HttpStatus.OK);
    }
    
    @GetMapping("/filter/status")
    public ResponseEntity<List<Candidate>> filterCandidatesByStatus(@RequestParam("status") CandidateStatus status) {
        List<Candidate> filteredCandidates = candidateService.filterCandidatesByStatus(status);
        return new ResponseEntity<>(filteredCandidates, HttpStatus.OK);
    }
}
