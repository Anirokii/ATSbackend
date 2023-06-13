package com.example.ATSproject.Modals;

import com.example.ATSproject.Enums.jobTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false , updatable = false)
    private Long id;
    private String jobTitle;
    private int experienceRequired;
    private double salary;
    private String location;
    private String department;
    private String jobImage;
    private String Description;
    private LocalDate postedDate;
    private jobTypeEnum jobType;
    @ManyToMany
    @JoinTable(
            name = "job_technologies",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologiesList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(int experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobImage() {
        return jobImage;
    }

    public void setJobImage(String jobImage) {
        this.jobImage = jobImage;
    }

    public jobTypeEnum getJobType() {
        return jobType;
    }

    public void setJobType(jobTypeEnum jobType) {
        this.jobType = jobType;
    }

    public List<Technology> getTechnologiesList() {
        return technologiesList;
    }

    public void setTechnologiesList(List<Technology> technologiesList) {
        this.technologiesList = technologiesList;
    }
}
