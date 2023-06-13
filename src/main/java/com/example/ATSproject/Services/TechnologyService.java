package com.example.ATSproject.Services;

import com.example.ATSproject.Exceptions.JobNotFoundException;
import com.example.ATSproject.Modals.Technology;
import com.example.ATSproject.Repos.TechnologyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {
    private final TechnologyRepo technologyRepo;

    @Autowired
    public TechnologyService(TechnologyRepo technologyRepo) {
        this.technologyRepo = technologyRepo;
    }

    //list of services

    public Technology addTechnology(Technology technology) {
        return technologyRepo.save(technology);
    }

    public Technology findTechnologyById ( Long id ){
        return technologyRepo.findById(id).orElseThrow(() -> new JobNotFoundException("User by id "+ id + "was not found"));
    }

    public List<Technology> findAllTechnologys(){
        return  technologyRepo.findAll();
    }

    public Technology updateTechnology (Technology technology){
        return technologyRepo.save(technology);
    }

    public void deleteTechnology( Long id){
        technologyRepo.deleteById(id);
    }

}
