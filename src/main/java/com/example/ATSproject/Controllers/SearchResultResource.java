package com.example.ATSproject.Controllers;

import com.example.ATSproject.Modals.Actors.Manager;
import com.example.ATSproject.Modals.SearchResult;
import com.example.ATSproject.Services.SearchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchResult")
public class SearchResultResource {
    public final SearchResultService searchResultService;

    @Autowired
    public SearchResultResource(SearchResultService searchResultService) {
        this.searchResultService = searchResultService;
    }

    //list of requestes

    @GetMapping("/all")
    public ResponseEntity<List<SearchResult>> getAllSearch(){
        List<SearchResult> searchResults = searchResultService.findAllSearch();
        return  new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SearchResult> getSearchByJobId (@PathVariable("id") Long id){
        SearchResult searchResult = searchResultService.findSearchByJobId(id);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<SearchResult> addSearchResult(@RequestBody SearchResult searchResult){
        SearchResult newSearchResult = searchResultService.addSearchResult(searchResult);
        return new ResponseEntity<>(newSearchResult, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SearchResult> updateSearchResult(@RequestBody SearchResult searchResult) {
        SearchResult updateSearchResult = searchResultService.updateSearchResult(searchResult);
        return new ResponseEntity<>(updateSearchResult, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSearchResult(@PathVariable ("id") Long id){
        searchResultService.deleteSearchResult(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}/candidates/delete/{candidateId}")
    public ResponseEntity<?> deleteCandidateFromSearch(@PathVariable ("jobId") Long searchId,@PathVariable ("candidateId") Long candidateId) throws ChangeSetPersister.NotFoundException {
        searchResultService.deleteCandidateFromSearch(searchId,candidateId);
        return ResponseEntity.noContent().build();
    }
}
