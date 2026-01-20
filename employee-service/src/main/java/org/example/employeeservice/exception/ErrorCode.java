package org.example.employeeservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    EMPLOYEE_EXISTED(1001, "Employee existed", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_FOUND(1002, "Employee not found", HttpStatus.NOT_FOUND),;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
