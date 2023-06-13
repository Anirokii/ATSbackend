package com.example.ATSproject.Controllers;

import com.example.ATSproject.Modals.Actors.Manager;
import com.example.ATSproject.Modals.Actors.ResponsableRH;
import com.example.ATSproject.Services.ResponsableRHService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsableRh")
public class ResponsableRhResource {
    public final ResponsableRHService responsableRHService;

    public ResponsableRhResource(ResponsableRHService responsableRHService) {
        this.responsableRHService = responsableRHService;
    }

    //list of requestes

    @GetMapping("/all")
    public ResponseEntity<List<ResponsableRH>> getAllResponsabeRHs(){
        List<ResponsableRH> responsabeRHs = responsableRHService.findAllResponsabeRHs();
        return  new ResponseEntity<>(responsabeRHs, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponsableRH> getResponsabeRHById (@PathVariable("id") Long id){
        ResponsableRH responsabeRH = responsableRHService.findResponsabeRHById(id);
        return new ResponseEntity<>(responsabeRH, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ResponsableRH> addResponsabeRH(@RequestBody ResponsableRH responsabeRH){
        ResponsableRH newResponsabeRH = responsableRHService.addResponsabeRH(responsabeRH);
        return new ResponseEntity<>(newResponsabeRH, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponsableRH> updateResponsabeRH(@RequestBody ResponsableRH responsabeRH) {
        ResponsableRH updateResponsabeRH = responsableRHService.updateResponsabeRH(responsabeRH);
        return new ResponseEntity<>(updateResponsabeRH, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteResponsabeRH(@PathVariable ("id") Long id){
        responsableRHService.deleteResponsabeRH(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
