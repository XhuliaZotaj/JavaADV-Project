package com.patient.PatientDB.Repo;

import com.patient.PatientDB.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository <Department, Long >  {
}
