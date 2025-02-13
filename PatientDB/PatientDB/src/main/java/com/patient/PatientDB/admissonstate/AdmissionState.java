package com.patient.PatientDB.admissonstate;

import com.patient.PatientDB.clinicalData.ClinicalData;
import com.patient.PatientDB.patient.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AdmissionState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDateTime enteringDate = LocalDateTime.now();
    @Column
    private LocalDateTime exitingDate;

    @Column
    private String cause;

    @Column
    private String reason;

    @Column
    private Boolean discharge = false;


    public AdmissionState() {
        this.enteringDate = LocalDateTime.now();
        this.discharge = false;
    }

    // Automatically set discharge to true if exitingDate is set
    public void setExitingDate(LocalDateTime exitingDate) {
        this.exitingDate = exitingDate;
        this.discharge = (exitingDate != null); // Set discharge to true if exitingDate is provided
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient; // Many admission states belong to one patient

    @OneToOne(mappedBy = "admissionState", cascade = CascadeType.ALL)
    private ClinicalData clinicalData; // One admission state has one clinical data

}
