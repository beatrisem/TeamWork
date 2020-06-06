package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.size() == 0;
    }

    public Iterable<String> getErrors() {
        return errors;
    }

}
