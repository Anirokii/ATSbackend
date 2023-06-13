package com.example.ATSproject.Services;

import com.example.ATSproject.Enums.CandidateStatus;
import com.example.ATSproject.Exceptions.JobNotFoundException;
import com.example.ATSproject.Modals.Candidate;
import com.example.ATSproject.Modals.SearchResult;
import com.example.ATSproject.Modals.Technology;
import com.example.ATSproject.Repos.CandidateRepo;
import com.example.ATSproject.Repos.SearchResultRepo;
import com.example.ATSproject.Repos.TechnologyRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    public final CandidateRepo candidateRepo;
    private final SearchResultRepo searchResultRepository;
    private final TechnologyRepo technologyRepo;

    @Autowired
    public CandidateService(CandidateRepo candidateRepo, SearchResultRepo searchResultRepository, TechnologyRepo technologyRepo) {
        this.candidateRepo = candidateRepo;
        this.searchResultRepository = searchResultRepository;
        this.technologyRepo = technologyRepo;
    }

    //list of services

    //add new candidate service
    @Transactional
    public Candidate addcandidate(Candidate newcandidate){
        return candidateRepo.save(newcandidate);
    }

    //find the candidate by ID
    public Candidate findCandidatebyId(Long id){
        return candidateRepo.findById(id).
                orElseThrow(() -> new JobNotFoundException("User by id "+ id + "was not found"));
    }
    public List<Candidate> findAllcandidate(){
        return candidateRepo.findAll();
    }

    //Update the candidate
    public Candidate updateCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    //delete a candidate

//    public void deleteCandidate(Long id){
//        candidateRepo.deleteById(id);
//    }

    @Transactional
    public void deleteCandidate(Long candidateId) {
        Optional<Candidate> candidateOptional = candidateRepo.findById(candidateId);
        Candidate candidate = candidateOptional.get();

        // Update search results
        List<SearchResult> searchResults = searchResultRepository.findAll();
        for (SearchResult searchResult : searchResults) {
            if (searchResult.getResultList().contains(candidate)) {
                searchResult.getResultList().remove(candidate);
                searchResultRepository.save(searchResult);
            }
        }
        // Delete candidate
        candidateRepo.delete(candidate);
    }


    //List of filters

    public List<Candidate> sortByScore(String sortOrder) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortOrder.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }
        Sort sort = Sort.by(direction, "score");
        return candidateRepo.findAll(sort);
    }

    public List<Candidate> filterCandidatesByExperienceLevel(int experienceLevel) {
        return candidateRepo.findByExperienceLevel(experienceLevel);
    }

    public List<Candidate> filterCandidatesByStatus(CandidateStatus status) {
        return candidateRepo.findByStatus(status);
    }
}
