package com.patient.PatientDB.Controller;

import com.patient.PatientDB.Repo.PatientRepo;
import com.patient.PatientDB.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientApiController {
    @Autowired
    private PatientRepo patientRepo;

    @GetMapping(value= "/patient")
    public List<Patient> getPatients(){
        return patientRepo.findAll();
    }

    @PostMapping(value="/savePatient")
    public String savePatient(@RequestBody Patient patient){
        patientRepo.save(patient);
        return "Patient Saved.";
    }

    @PutMapping(value="/updatedPatient/{id}")
    public String updatePatient(@PathVariable long id,@RequestBody Patient patient){
        Patient updatedPatient = patientRepo.findById(id).get();
        updatedPatient.setFirstName(patient.getFirstName());
        updatedPatient.setLastName(patient.getLastName());
        patientRepo.save(updatedPatient);
        return "Updated Patient.";
    }

    @DeleteMapping(value = "/deletePatient/{id}")
    public String deletePatient(@PathVariable long id) {
        long patientCount = patientRepo.count(); // Count total patients

        if (patientCount == 1) {
            return "Warning: Cannot delete. Only one patient left in the department.";
        }

        return patientRepo.findById(id).map(patient -> {
            patientRepo.delete(patient);
            return "Patient with id: " + id + " is deleted";
        }).orElse("Patient not found with id: " + id);
    }
}
