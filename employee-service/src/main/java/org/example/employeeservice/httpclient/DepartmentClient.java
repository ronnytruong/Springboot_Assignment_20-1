package org.example.employeeservice.httpclient;

import org.example.employeeservice.dto.response.ApiResponse;
import org.example.employeeservice.dto.response.DepartmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "department-service",
        url = "http://localhost:8080"
)
public interface DepartmentClient {

    @GetMapping("department/departments/{id}")
    DepartmentResponse findById(@PathVariable int id);
}
