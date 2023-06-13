package com.example.ATSproject.Controllers;

import com.example.ATSproject.Modals.Actors.Manager;
import com.example.ATSproject.Modals.Job;
import com.example.ATSproject.Services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerResource {
    public final ManagerService managerService;

    @Autowired
    public ManagerResource(ManagerService managerService) {
        this.managerService = managerService;
    }

    //list of requestes

    @GetMapping("/all")
    public ResponseEntity<List<Manager>> getAllManagers(){
        List<Manager> managers = managerService.findAllManagers();
        return  new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Manager> getManagerById (@PathVariable("id") Long id){
        Manager manager = managerService.findManagerById(id);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Manager> addManager(@RequestBody Manager manager){
        Manager newManager = managerService.addManager(manager);
        return new ResponseEntity<>(newManager, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager) {
        Manager updateManager = managerService.updateManager(manager);
        return new ResponseEntity<>(updateManager, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManager(@PathVariable ("id") Long id){
        managerService.deleteManager(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }


}
