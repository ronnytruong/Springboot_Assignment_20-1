package org.example.employeeservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.employeeservice.dto.request.EmployeeCreationRequest;
import org.example.employeeservice.dto.request.EmployeeUpdateRequest;
import org.example.employeeservice.dto.response.ApiResponse;
import org.example.employeeservice.dto.response.EmployeeResponse;
import org.example.employeeservice.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmployeeController {
    EmployeeService employeeService;

    @GetMapping
    public ApiResponse<List<EmployeeResponse>> findAll() {
        return ApiResponse.<List<EmployeeResponse>>builder()
                .result(employeeService.getEmployees())
                .build();
    }

    @PostMapping
    public ApiResponse<EmployeeResponse> createEmployee(
            @RequestBody EmployeeCreationRequest request) {

        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.createEmployee(request))
                .build();
    }

    @PatchMapping("/{employeeId}")
    public ApiResponse<EmployeeResponse> updateEmployee(
            @PathVariable String employeeId, @RequestBody EmployeeUpdateRequest request) {

        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.updateEmployee(employeeId, request))
                .build();
    }

    @DeleteMapping
    public ApiResponse<String> deleteEmployee(@RequestParam String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ApiResponse.<String>builder()
                .result("Employee has been deleted")
                .build();
    }

    @PostMapping("/assign-department")
    public ApiResponse<EmployeeResponse> addToDepartment(
            @RequestParam String employeeId,
            @RequestParam Integer departmentId) {
        log.info("Employee Id: {}, Employee Id: {}", employeeId, departmentId);

        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.addToDepartment(departmentId, employeeId))
                .build();
    }

    @PostMapping("/remove-department")
    public ApiResponse<EmployeeResponse> removeFromDepartment(
            @RequestParam String employeeId,
            @RequestParam Integer departmentId) {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.removeFromDepartment(departmentId, employeeId))
                .build();
    }
}
