package org.example.employeeservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    String id;
    String fullName;
    String address;
    String phone;
    Integer department;
    Boolean status;
}
