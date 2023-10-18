package com.example.demo.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Provider {
    LOCAL("Local"),
    GOOGLE("Google");
    public final String value;
}