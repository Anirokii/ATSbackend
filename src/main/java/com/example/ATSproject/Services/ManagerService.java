package com.example.ATSproject.Services;

import com.example.ATSproject.Exceptions.JobNotFoundException;
import com.example.ATSproject.Modals.Actors.Manager;
import com.example.ATSproject.Modals.Technology;
import com.example.ATSproject.Repos.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    public final ManagerRepo managerRepo;

    @Autowired
    public ManagerService(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }

    //list of services

    public Manager addManager(Manager manager) {
        return managerRepo.save(manager);
    }

    public Manager findManagerById ( Long id ){
        return managerRepo.findById(id).orElseThrow(() -> new JobNotFoundException("Manager by id "+ id + "was not found"));
    }

    public List<Manager> findAllManagers(){
        return  managerRepo.findAll();
    }

    public Manager updateManager (Manager manager){
        return managerRepo.save(manager);
    }

    public void deleteManager( Long id){
        managerRepo.deleteById(id);
    }
}
