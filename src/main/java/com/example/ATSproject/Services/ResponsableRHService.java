package com.example.ATSproject.Services;

import com.example.ATSproject.Exceptions.JobNotFoundException;
import com.example.ATSproject.Modals.Actors.ResponsableRH;
import com.example.ATSproject.Modals.Technology;
import com.example.ATSproject.Repos.ResponsibleRhRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsableRHService {
    public final ResponsibleRhRepo responsibleRhRepo;

    @Autowired
    public ResponsableRHService(ResponsibleRhRepo responsibleRhRepo) {
        this.responsibleRhRepo = responsibleRhRepo;
    }

    //list of services

    public ResponsableRH addResponsabeRH(ResponsableRH responsabeRH) {
        return responsibleRhRepo.save(responsabeRH);
    }

    public ResponsableRH findResponsabeRHById ( Long id ){
        return responsibleRhRepo.findById(id).orElseThrow(() -> new JobNotFoundException("ResponsabeRH by id "+ id + "was not found"));
    }

    public List<ResponsableRH> findAllResponsabeRHs(){
        return  responsibleRhRepo.findAll();
    }

    public ResponsableRH updateResponsabeRH (ResponsableRH responsabeRH){
        return responsibleRhRepo.save(responsabeRH);
    }

    public void deleteResponsabeRH( Long id){
        responsibleRhRepo.deleteById(id);
    }

}
