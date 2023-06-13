package com.example.ATSproject.Controllers;

import com.example.ATSproject.Modals.Actors.Manager;
import com.example.ATSproject.Modals.Cv;
import com.example.ATSproject.Services.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cv")
public class CvResource {
    public final CvService cvService;

    @Autowired
    public CvResource(CvService cvService) {
        this.cvService = cvService;
    }

    //list of requestes

    @GetMapping("/all")
    public ResponseEntity<List<Cv>> getAllCvs(){
        List<Cv> cvs = cvService.findAllCvs();
        return  new ResponseEntity<>(cvs, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable Long id) {
        Cv document = cvService.findCvbyId(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(document.getFile());
    }
    @PostMapping("/add")
    public Cv uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {
        return cvService.addCv(file);
    }

    @PutMapping("/update")
    public ResponseEntity<Cv> updateCv(@RequestBody Cv cv) {
        Cv updateCv = cvService.updateCv(cv);
        return new ResponseEntity<>(updateCv, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCv(@PathVariable ("id") Long id){
        cvService.deleteCv(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
