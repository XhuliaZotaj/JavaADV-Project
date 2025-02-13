package com.patient.PatientDB.Controller;

import com.patient.PatientDB.Repo.DepartmentRepo;
import com.patient.PatientDB.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DepartmentApiController {

    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping(value= "/departments")
    public List<Department> getDepartments(){
        return departmentRepo.findAll();
    }

    @PostMapping(value="/saveDepartment")
    public String saveDepartment(@RequestBody Department department){
        departmentRepo.save(department);
        return "Department Saved.";
    }

    @PutMapping(value="/updateDepartment/{id}")
    public String updateDepartment(@PathVariable long id, @RequestBody Department department){
        return departmentRepo.findById(id).map(updatedDepartment -> {
            updatedDepartment.setCode(department.getCode());
            updatedDepartment.setName(department.getName());
            departmentRepo.save(updatedDepartment);
            return "Updated Department.";
        }).orElse("Department not found with id: " + id);
    }

    @DeleteMapping(value = "/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable long id) {
        return departmentRepo.findById(id).map(department -> {
            departmentRepo.delete(department);
            return "Department with id: " + id + " is deleted";
        }).orElse("Department not found with id: " + id);
    }
}