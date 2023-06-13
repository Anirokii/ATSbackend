package com.example.ATSproject.Modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToMany
    @JoinTable(
            name = "search_result_candidates",
            joinColumns = @JoinColumn(name = "search_result_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    private List<Candidate> resultList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Candidate> getResultList() {
        return resultList;
    }

    public void setResultList(List<Candidate> resultList) {
        this.resultList = resultList;
    }
}
