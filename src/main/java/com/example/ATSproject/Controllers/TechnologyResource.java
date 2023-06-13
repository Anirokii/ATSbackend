package com.example.ATSproject.Controllers;

import com.example.ATSproject.Modals.Technology;
import com.example.ATSproject.Services.TechnologyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technology")
public class TechnologyResource {
    private final TechnologyService technologyService ;

    public TechnologyResource(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Technology>> getAllTechnologies (){
        List<Technology> technologies = technologyService.findAllTechnologys();
        return  new ResponseEntity<>(technologies, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Technology> getTechnologyById (@PathVariable("id") Long id){
        Technology technology = technologyService.findTechnologyById(id);
        return new ResponseEntity<>(technology, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Technology> addTechnology(@RequestBody Technology technology){
        Technology newTechnology = technologyService.addTechnology(technology);
        return new ResponseEntity<>(newTechnology, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Technology> updateTechnology(@RequestBody Technology technology) {
        Technology updateTechnology = technologyService.updateTechnology(technology);
        return new ResponseEntity<>(updateTechnology, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTechnology(@PathVariable ("id") Long id){
        technologyService.deleteTechnology(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}

