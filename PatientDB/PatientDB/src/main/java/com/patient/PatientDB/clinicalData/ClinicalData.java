package com.patient.PatientDB.clinicalData;

import com.patient.PatientDB.admissonstate.AdmissionState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class ClinicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String clinicalRecord;

    @OneToOne
    @JoinColumn(name = "admission_state_id")
    private AdmissionState admissionState; // One-to-One relationship
}
