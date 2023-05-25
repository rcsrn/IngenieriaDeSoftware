package com.michelin.api.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDto {
    
    @Id
    @Column(name = "email")
    @NotNull
    @JsonProperty("email")
    private String email;

    @Column(name = "password")
    @NotNull
    @JsonProperty("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
