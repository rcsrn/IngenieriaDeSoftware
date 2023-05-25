package com.michelin.api.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;
import com.michelin.api.dto.LoginDto;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.entity.Product;
import com.michelin.api.service.SvcClient;
import com.michelin.exception.ApiException;

@RestController
@RequestMapping("/michelin")
public class CtrlClient {   

    @Autowired
    SvcClient svc;

    @PostMapping("/register") 
    public ResponseEntity<ApiResponse> registerClient(@Valid @RequestBody ClientDto client, BindingResult bindingResult) throws MessagingException {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        return new ResponseEntity<>(svc.registerClient(client), HttpStatus.OK);
    }

    @PutMapping("/update/password/{client_id}")
    public ResponseEntity<ApiResponse> updatePassword(@PathVariable Integer client_id, @Valid @RequestBody PasswordDto in, BindingResult bindingResult) throws MessagingException {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        return new ResponseEntity<>(svc.updatePassword(in, client_id), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(svc.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/products/{product_id}/buy/{client_id}")
    public ResponseEntity<ApiResponse> buyProduct(@PathVariable Integer product_id,
    @PathVariable Integer client_id) {
        return new ResponseEntity<>(svc.createOrder(product_id, client_id), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDto in, BindingResult bindingResult) { 
        return new ResponseEntity<>(svc.login(in), HttpStatus.OK);
    }
}
