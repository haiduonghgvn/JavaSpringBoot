package com.example.demo.service;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult {
    private Status status = Status.SUCCESS;
    private String message;
    private Object data;
    public enum Status {
        SUCCESS, FAILED;
    }

}
