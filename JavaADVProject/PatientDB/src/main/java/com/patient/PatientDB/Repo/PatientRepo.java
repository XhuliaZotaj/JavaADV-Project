package com.patient.PatientDB.Repo;

import com.patient.PatientDB.patient.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository <Patient, Long > {
}
