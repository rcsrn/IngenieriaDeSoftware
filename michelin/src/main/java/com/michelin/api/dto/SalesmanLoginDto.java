package com.michelin.api.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;


public class SalesmanLoginDto {
    
    @Column(name = "email")
    @NotNull(message = "email is required")
    private String email;
    
    @Column(name = "password")
    @NotNull(message = "password is required")
    private String password;

    // Agrega los getters y setters


    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
