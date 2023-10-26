package ru.malik.todolist.errorhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomException {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private HttpStatus httpStatus;

    private String message;

    public CustomException(LocalDateTime timestamp, HttpStatus httpStatus, String message) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "timestamp=" + timestamp +
                ", httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
