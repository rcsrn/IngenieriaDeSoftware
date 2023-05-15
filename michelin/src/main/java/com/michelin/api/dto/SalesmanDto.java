package com.michelin.api.dto;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "salesman")
public class SalesmanDto {

    @Id
    @Column(name = "email")
    @NotNull(message = "email is required")
    private String email;

    @Column(name = "name")
    @NotNull(message = "name is required")
    private String name;

    @JsonProperty("date_of_birth")
    @Column(name = "date_of_birth")
    @NotNull(message = "date of birth is required")
    private String date_of_birth;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
