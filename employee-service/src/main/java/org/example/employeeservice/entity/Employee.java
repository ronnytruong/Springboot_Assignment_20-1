package org.example.employeeservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "full_name", columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String fullName;

    String address;
    String phone;
    Boolean status;

    @Column(name = "department_id")
    Integer departmentId;
}
