package com.penchev.perfume.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ExceptionValidationResponse {
    private Date timestamp;
    private String path;
    private List<String> errors;
}
