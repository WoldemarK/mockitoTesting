package com.example.mockitotesting.model;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String password;

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User() {
    }
}

