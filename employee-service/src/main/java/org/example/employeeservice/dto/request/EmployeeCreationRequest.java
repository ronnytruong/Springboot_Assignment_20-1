package org.example.employeeservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeCreationRequest {
    String fullName;
    String address;
    String phone;
    Integer departmentId;
}
