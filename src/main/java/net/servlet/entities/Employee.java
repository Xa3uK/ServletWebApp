package net.servlet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private Long departmentId;
    private Long chiefId;
    private String name;
    private int salary;

    public Employee(Long departmentId, Long chiefId, String name, int salary) {
        this.departmentId = departmentId;
        this.chiefId = chiefId;
        this.name = name;
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setChiefId(Long chiefId) {
        this.chiefId = chiefId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
