package com.example.intermediate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "EMPLOYEE_DEPARTMENT")
@Table(name = "TBL_EMPLOYEE")
@Getter @Setter @ToString
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;
    @Column(name = "EMPLOYEE_BIRTH")
    private LocalDateTime employeeBirth;
    @Column(name = "EMPLOYEE_CAREER")
    private int employeeCareer;

    public void create(String employeeName, LocalDateTime employeeBirth, int employeeCareer) {
        this.employeeName = employeeName;
        this.employeeBirth = employeeBirth;
        this.employeeCareer = employeeCareer;
    }
}
