package com.example.ATSproject.Modals;

import com.example.ATSproject.Enums.InterviewStatus;
import com.example.ATSproject.Modals.Actors.Manager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false , updatable = false)
    private Long id;
    private InterviewStatus interviewStatus;
    private Date dateInterview;
    private int result;
    private String comment;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

}
