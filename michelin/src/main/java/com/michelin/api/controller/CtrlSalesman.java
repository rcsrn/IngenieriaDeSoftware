package com.michelin.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.service.SvcSalesman;
import com.michelin.exception.ApiException;


@RestController
@RequestMapping("/michelin/salesman")
public class CtrlSalesman {
    @Autowired
    SvcSalesman svc;

    @PutMapping("/update/password/{salesman_id}")
    public ResponseEntity<ApiResponse> updatePassword(@PathVariable Integer salesman_id, @Valid @RequestBody PasswordDto in, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseEntity<>(svc.updatePassword(in, salesman_id), HttpStatus.OK);
    }

}
