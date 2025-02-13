package com.patient.PatientDB.Repo;

import com.patient.PatientDB.clinicalData.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalDataRepo extends JpaRepository<ClinicalData, Long> {
}
