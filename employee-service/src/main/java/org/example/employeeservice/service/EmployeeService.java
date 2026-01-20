package org.example.employeeservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.employeeservice.dto.request.EmployeeCreationRequest;
import org.example.employeeservice.dto.request.EmployeeUpdateRequest;
import org.example.employeeservice.dto.response.ApiResponse;
import org.example.employeeservice.dto.response.DepartmentResponse;
import org.example.employeeservice.dto.response.EmployeeResponse;
import org.example.employeeservice.entity.Employee;
import org.example.employeeservice.exception.AppException;
import org.example.employeeservice.exception.ErrorCode;
import org.example.employeeservice.httpclient.DepartmentClient;
import org.example.employeeservice.mapper.EmployeeMapper;
import org.example.employeeservice.repository.EmployeeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmployeeService {
    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;
    DepartmentClient departmentClient;


    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeResponse).toList();
    }

    public EmployeeResponse createEmployee(EmployeeCreationRequest request) {

        if (employeeRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.EMPLOYEE_EXISTED);
        }

        Employee employee = employeeMapper.toEmployee(request);
        employee.setStatus(true);

        employeeRepository.save(employee);

        return employeeMapper.toEmployeeResponse(employee);
    }


    public EmployeeResponse updateEmployee(String employeeId, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));

        employeeMapper.toEmployee(employee, request);
        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public void deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(employee);
    }

    public EmployeeResponse addToDepartment(Integer departmentId, String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));

        DepartmentResponse departmentResponse = departmentClient.getDepartment(departmentId);

        employee.setDepartment(departmentResponse.getId());
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }

    public EmployeeResponse removeFromDepartment(Integer departmentId, String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));

        DepartmentResponse departmentResponse = departmentClient.getDepartment(departmentId);

        if(!employee.getDepartment().equals(departmentResponse.getId())) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }

        employee.setDepartment(null);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }
}
