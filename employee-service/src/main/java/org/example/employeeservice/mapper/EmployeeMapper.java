package org.example.employeeservice.mapper;

import org.example.employeeservice.dto.request.EmployeeCreationRequest;
import org.example.employeeservice.dto.request.EmployeeUpdateRequest;
import org.example.employeeservice.dto.response.EmployeeResponse;
import org.example.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeResponse toEmployeeResponse(Employee employee);
    Employee toEmployee(EmployeeCreationRequest request);
    void toEmployee(@MappingTarget Employee employee, EmployeeUpdateRequest request);
}
