package org.example.departmentservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.departmentservice.dto.request.DepartmentCreationRequest;
import org.example.departmentservice.dto.request.DepartmentUpdateRequest;
import org.example.departmentservice.dto.response.DepartmentResponse;
import org.example.departmentservice.entity.Department;
import org.example.departmentservice.exception.AppException;
import org.example.departmentservice.exception.ErrorCode;
import org.example.departmentservice.mapper.DepartmentMapper;
import org.example.departmentservice.repository.DepartmentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DepartmentService {
    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;

    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDepartmentResponse).toList();
    }

    public DepartmentResponse getDepartment(int id) throws AppException {
        Department department = departmentRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        return departmentMapper.toDepartmentResponse(department);
    }

    public DepartmentResponse createDepartment(DepartmentCreationRequest request) {
        Department department = departmentMapper.toDepartment(request);
        department.setStatus(true);
        if(departmentRepository.existsByName(department.getName())) {
            throw new AppException(ErrorCode.DEPARTMENT_EXISTED);
        }
        departmentRepository.save(department);
        return departmentMapper.toDepartmentResponse(department);
    }

    public DepartmentResponse updateDepartment(int departmentId, DepartmentUpdateRequest request) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        departmentMapper.toDepartment(department, request);
        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }

    public void deleteDepartment(int departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        try {
            departmentRepository.delete(department);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.DEPARTMENT_IN_USE);
        }
    }
}
