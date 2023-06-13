package com.example.ATSproject.Repos;

import com.example.ATSproject.Modals.Candidate;
import com.example.ATSproject.Modals.SearchResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchResultRepo extends JpaRepository<SearchResult,Long> {
    Optional<SearchResult> findSearchResultByJobId(Long id);

    List<SearchResult> findByJobId(Long jobId);
}
