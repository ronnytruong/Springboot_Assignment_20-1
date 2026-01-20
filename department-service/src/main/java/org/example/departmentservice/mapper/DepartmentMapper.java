package org.example.departmentservice.mapper;

import org.example.departmentservice.dto.request.DepartmentCreationRequest;
import org.example.departmentservice.dto.request.DepartmentUpdateRequest;
import org.example.departmentservice.dto.response.DepartmentResponse;
import org.example.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy =
        NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {
    DepartmentResponse toDepartmentResponse(Department department);
    Department toDepartment(DepartmentCreationRequest request);

    void toDepartment(@MappingTarget Department department, DepartmentUpdateRequest request);
}
