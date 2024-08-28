package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResp {
    private Integer statusCode;
    private String descriptionCode;
    private String messageError;
    private List<ErrorDetail> errors;
    private String path;
}
