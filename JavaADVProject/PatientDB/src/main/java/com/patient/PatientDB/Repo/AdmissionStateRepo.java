package com.patient.PatientDB.Repo;

import com.patient.PatientDB.admissonstate.AdmissionState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionStateRepo extends JpaRepository <AdmissionState, Long> {
}
