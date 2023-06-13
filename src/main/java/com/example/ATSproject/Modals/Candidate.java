package com.example.ATSproject.Modals;

import com.example.ATSproject.Enums.CandidateStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false , updatable = false)
    private Long id;
    private String fullName;
    private String phone;
    @Lob
    @Column(name = "profile_image")
    private byte[] imgProfil;
    private String educationLevel;
    private String university;
    private String location;
    private Date dateOfBirth;
    @Transient
    private int age;
    private int experienceLevel;
    @ManyToMany
    @JoinTable(
            name = "candidate_technologies",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologiesList = new ArrayList<>();
    private int score;
    @Column(name = "cv_score", nullable = false, columnDefinition = "integer default 0")
    private Integer cvScore;
    @Column(name = "exp_score", nullable = false, columnDefinition = "integer default 0")
    private int expScore;
    @Column(name = "interview_score", nullable = false, columnDefinition = "integer default 0")
    private int interviewScore;
    private CandidateStatus status;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImgProfil() {
        return imgProfil;
    }

    public void setImgProfil(byte[] imgProfil) {
        this.imgProfil = imgProfil;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public List<Technology> getTechnologiesList() {
        return technologiesList;
    }

    public void setTechnologiesList(ArrayList<Technology> technologiesList) {
        this.technologiesList = technologiesList;
    }

    public CandidateStatus getStatus() {
        return status;
    }

    public void setStatus(CandidateStatus status) {
        this.status = status;
    }

    public void recalculateScore() {
        score = cvScore + expScore + interviewScore;
    }

    public void setCvScore(int cvScore) {
        this.cvScore = cvScore;
        recalculateScore();
    }

    public void setExpScore(int expScore) {
        this.expScore = expScore;
        recalculateScore();
    }

    public void setInterviewScore(int interviewScore) {
        this.interviewScore = interviewScore;
        recalculateScore();
    }

    public int getScore() {
        return score;
    }

    public Integer getCvScore() {
        return cvScore;
    }

    public int getExpScore() {
        return expScore;
    }

    public int getInterviewScore() {
        return interviewScore;
    }

    public int getAge() {
        if (dateOfBirth != null) {
            LocalDate currentDate = LocalDate.now();
            LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDate, currentDate).getYears();
        }
        return 0;
    }
}
