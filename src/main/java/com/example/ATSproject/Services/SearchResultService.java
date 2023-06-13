package com.example.ATSproject.Services;

import com.example.ATSproject.Exceptions.JobNotFoundException;
import com.example.ATSproject.Modals.Candidate;
import com.example.ATSproject.Modals.Job;
import com.example.ATSproject.Modals.SearchResult;
import com.example.ATSproject.Modals.Technology;
import com.example.ATSproject.Repos.CandidateRepo;
import com.example.ATSproject.Repos.JobRepo;
import com.example.ATSproject.Repos.SearchResultRepo;
import com.example.ATSproject.Repos.TechnologyRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchResultService {
    public final SearchResultRepo searchResultRepo;
    public final CandidateRepo candidateRepo;
    private final JobRepo jobRepo;
    private final TechnologyRepo technologyRepo;

    private final JobService jobService;
    private final CandidateService candidateService;


    @Autowired
    public SearchResultService(SearchResultRepo searchResultRepo, CandidateRepo candidateRepo, JobRepo jobRepo, TechnologyRepo technologyRepo, JobService jobService, CandidateService candidateService) {
        this.searchResultRepo = searchResultRepo;
        this.candidateRepo = candidateRepo;
        this.jobRepo = jobRepo;
        this.technologyRepo = technologyRepo;
        this.jobService = jobService;
        this.candidateService = candidateService;
    }

    //list of services

    //add new searchResult service
    @Transactional
    public SearchResult addSearchResult(SearchResult newSearchResult){

        Job job = newSearchResult.getJob();
        if (job != null && job.getId() == null) { // if the job is not persisted
            List<Technology> technologies = job.getTechnologiesList();
            if (technologies != null) {
                for (Technology technology : technologies) {
                    if (technology.getId() == null || !technologyRepo.existsById(technology.getId())) {
                        technologyRepo.save(technology);
                    }
                }
            }
            jobRepo.save(job); // save the Job first
        }

        // check if candidates are not persisted and technologies exist
        List<Candidate> candidates = newSearchResult.getResultList();
        if (candidates != null) {
            for (Candidate candidate : candidates) {
                if (candidate.getId() == null || !candidateRepo.existsById(candidate.getId())) {
                    candidateRepo.save(candidate); // save the Candidate if it does not exist
                }
                List<Technology> technologies = candidate.getTechnologiesList();
                if (technologies != null) {
                    for (Technology technology : technologies) {
                        if (technology.getId() == null || !technologyRepo.existsById(technology.getId())) {
                            technologyRepo.save(technology); // save the Technology if it does not exist
                        }
                    }
                }
            }
        }

        return searchResultRepo.save(newSearchResult); // then save the SearchResult
    }

    //find the searchResult by ID
    public SearchResult findSearchByJobId(Long id){
        return searchResultRepo.findSearchResultByJobId(id).
                orElseThrow(() -> new JobNotFoundException("User by id "+ id + "was not found"));
    }
    public List<SearchResult> findAllSearch(){
        return searchResultRepo.findAll();
    }

    //update a search result
    public SearchResult updateSearchResult (SearchResult searchResult){
        return searchResultRepo.save(searchResult);
    }

    //delete a searchResult
    public void deleteSearchResult(Long id){
        searchResultRepo.deleteById(id);
    }

    public void deleteCandidateById(Long candidateId) {
        candidateRepo.deleteById(candidateId);
    }

    public void deleteCandidateFromSearch(Long jobId, Long candidateId) throws ChangeSetPersister.NotFoundException {
        SearchResult search = searchResultRepo.findSearchResultByJobId(jobId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        List<Candidate> resultList = search.getResultList();
        resultList.removeIf(candidate -> candidate.getId().equals(candidateId));

        searchResultRepo.save(search);
    }

}
