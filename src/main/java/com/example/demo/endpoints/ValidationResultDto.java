package com.example.demo.endpoints;

import com.example.demo.data.ValidationResult;

import java.util.ArrayList;
import java.util.List;

public class ValidationResultDto {

    public ValidationResultDto(ValidationResult result, Object data) {
        errors = new ArrayList<>();

        for (var error:result.getErrors()) {
            errors.add(error);
        }

        this.data = data;
    }

    public ValidationResultDto(String error) {
        errors = new ArrayList<>();
        errors.add(error);
    }

    private List<String> errors;

    private Object data;

    public boolean isValid() {
        return errors.size() == 0;
    }

    public Iterable<String> getErrors() {
        return errors;
    }

}
