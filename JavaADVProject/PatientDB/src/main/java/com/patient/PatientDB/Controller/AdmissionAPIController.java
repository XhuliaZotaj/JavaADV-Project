package com.patient.PatientDB.Controller;
import com.patient.PatientDB.Repo.AdmissionStateRepo;
import com.patient.PatientDB.admissonstate.AdmissionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

    @RestController
    public class AdmissionAPIController {
        @Autowired
        private AdmissionStateRepo admissionStateRepo;

        @GetMapping(value= "/admissions")
        public List<AdmissionState> getAdmissions(){
            return admissionStateRepo.findAll();
        }

        @PostMapping(value="/saveAdmission")
        public String saveAdmission(@RequestBody AdmissionState admissionState){
            admissionState.setEnteringDate(LocalDateTime.now()); // Set entering date to now
            admissionState.setDischarge(false); // Default to false
            admissionStateRepo.save(admissionState);
            return "Admission Saved.";
        }

        @PutMapping(value="/updateAdmission/{id}")
        public String updateAdmission(@PathVariable long id, @RequestBody AdmissionState admissionState){
            return admissionStateRepo.findById(id).map(updatedAdmission -> {
                updatedAdmission.setExitingDate(admissionState.getExitingDate());
                updatedAdmission.setCause(admissionState.getCause());
                updatedAdmission.setReason(admissionState.getReason());
                updatedAdmission.setDischarge(admissionState.getExitingDate() != null); // Discharge if exiting date is set
                admissionStateRepo.save(updatedAdmission);
                return "Updated Admission.";
            }).orElse("Admission not found with id: " + id);
        }

        @DeleteMapping(value = "/deleteAdmission/{id}")
        public String deleteAdmission(@PathVariable long id) {
            return admissionStateRepo.findById(id).map(admission -> {
                admissionStateRepo.delete(admission);
                return "Admission with id: " + id + " is deleted";
            }).orElse("Admission not found with id: " + id);
        }
    }