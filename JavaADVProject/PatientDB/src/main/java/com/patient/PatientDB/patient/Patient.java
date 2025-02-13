package com.patient.PatientDB.patient;
import com.patient.PatientDB.admissonstate.AdmissionState;
import com.patient.PatientDB.department.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<AdmissionState> admissionStates; // One patient can have multiple admission states

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department; // Many patients belong to one department

}
