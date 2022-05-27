package com.gymscheduler.gymscheduler.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorMessage {
    private String field;
    private String message;

    public FieldErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
