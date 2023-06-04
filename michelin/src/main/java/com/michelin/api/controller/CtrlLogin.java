package com.michelin.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.LoginDto;
import com.michelin.api.service.SvcAdmin;
import com.michelin.api.service.SvcClient;
import com.michelin.api.service.SvcSalesman;
import com.michelin.exception.ApiException;

@RestController
@RequestMapping("/michelin")
public class CtrlLogin {
    
    @Autowired
    SvcClient svc;

    @Autowired
    SvcSalesman svcSalesman;

    @Autowired
    SvcAdmin svcAdmin;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDto in, BindingResult bindingResult) { 
        ApiResponse response = svc.login(in);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response = svcSalesman.loginSalesman(in);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response = svcAdmin.loginAdmin(in);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        System.out.println(response);

        throw new ApiException(HttpStatus.NOT_FOUND, "email incorrecto!");
    }
}
