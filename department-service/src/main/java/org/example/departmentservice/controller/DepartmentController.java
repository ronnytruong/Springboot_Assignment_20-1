package org.example.departmentservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.departmentservice.dto.request.DepartmentCreationRequest;
import org.example.departmentservice.dto.request.DepartmentUpdateRequest;
import org.example.departmentservice.dto.response.ApiResponse;
import org.example.departmentservice.dto.response.DepartmentResponse;
import org.example.departmentservice.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DepartmentController {
    DepartmentService departmentService;

    @GetMapping
    public ApiResponse<List<DepartmentResponse>> findAll() {
        return ApiResponse.<List<DepartmentResponse>>builder()
                .result(departmentService.getDepartments())
                .build();
    }

    @PostMapping
    public ApiResponse<DepartmentResponse> createDepartment(
            @RequestBody DepartmentCreationRequest request) {
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.createDepartment(request))
                .build();
    }

    @PatchMapping("/{departmentId}")
    public ApiResponse<DepartmentResponse> updateDepartment(
            @PathVariable int departmentId, @RequestBody DepartmentUpdateRequest request) {
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.updateDepartment(departmentId, request))
                .build();
    }

    @DeleteMapping("/departmentId")
    public ApiResponse<String> deleteDepartment(@PathVariable int departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ApiResponse.<String>builder()
                .result("Department has been deleted")
                .build();
    }
}
