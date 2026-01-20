package org.example.departmentservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    DEPARTMENT_EXISTED(1001, "Department existed", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NOT_FOUND(1002, "Department not found", HttpStatus.NOT_FOUND),
    DEPARTMENT_IN_USE(1003, "Department is in use", HttpStatus.CONFLICT)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
