package com.patient.PatientDB.Controller;

import com.patient.PatientDB.Repo.ClinicalDataRepo;
import com.patient.PatientDB.clinicalData.ClinicalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CDataAPIController {

    @Autowired
    private ClinicalDataRepo clinicalDataRepo;

    @GetMapping(value= "/clinicalData")
    public List<ClinicalData> getClinicalData(){
        return clinicalDataRepo.findAll();
    }

    @PostMapping(value="/saveClinicalData")
    public String saveClinicalData(@RequestBody ClinicalData clinicalData){
        clinicalDataRepo.save(clinicalData);
        return "Clinical Data Saved.";
    }

    @PutMapping(value="/updateClinicalData/{id}")
    public String updateClinicalData(@PathVariable long id, @RequestBody ClinicalData clinicalData){
        return clinicalDataRepo.findById(id).map(updatedClinicalData -> {
            updatedClinicalData.setClinicalRecord(clinicalData.getClinicalRecord());
            clinicalDataRepo.save(updatedClinicalData);
            return "Updated Clinical Data.";
        }).orElse("Clinical Data not found with id: " + id);
    }

    @DeleteMapping(value = "/deleteClinicalData/{id}")
    public String deleteClinicalData(@PathVariable long id) {
        return clinicalDataRepo.findById(id).map(clinicalData -> {
            clinicalDataRepo.delete(clinicalData);
            return "Clinical Data with id: " + id + " is deleted";
        }).orElse("Clinical Data not found with id: " + id);
    }
}

