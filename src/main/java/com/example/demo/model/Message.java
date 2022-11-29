package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }
}
