package com.example.employeeinfo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseData<T> {

    private String message;
    private boolean success;
    private T data;



    public ResponseData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}

