package com.example.ATSproject.Services;

import com.example.ATSproject.Exceptions.JobNotFoundException;
import com.example.ATSproject.Modals.Candidate;
import com.example.ATSproject.Modals.Cv;
import com.example.ATSproject.Repos.CvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CvService {
    public final CvRepo cvRepo;
    @Autowired
    public CvService(CvRepo cvRepo) {
        this.cvRepo = cvRepo;
    }

    //list of services

    public Cv addCv(MultipartFile file) throws IOException {
        Cv document = new Cv();
        document.setName(file.getOriginalFilename());
        document.setFile(file.getBytes());

        return cvRepo.save(document);
    }
    //find the candidate by ID
    public Cv findCvbyId(Long id){
        return cvRepo.findById(id).
                orElseThrow(() -> new JobNotFoundException("Cv by id "+ id + "was not found"));
    }
    public List<Cv> findAllCvs(){
        return cvRepo.findAll();
    }

    //Update the candidate
    public Cv updateCv(Cv cv) {
        return cvRepo.save(cv);
    }

    //delete a candidate

    public void deleteCv(Long id){
        cvRepo.deleteById(id);
    }
}
