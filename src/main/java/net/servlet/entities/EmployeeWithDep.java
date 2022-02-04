package net.servlet.entities;

import lombok.Data;

@Data
public class EmployeeWithDep extends Employee{
    private String departmentName;

    public EmployeeWithDep(Long id, Long departmentId, Long chiefId, String name, int salary, String departmentName) {
        super(id, departmentId, chiefId, name, salary);
        this.departmentName = departmentName;
    }
}
