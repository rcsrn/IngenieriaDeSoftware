package com.michelin.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ProductDto;
import com.michelin.api.dto.SalesmanDto;
import com.michelin.api.entity.Client;
import com.michelin.api.entity.Product;
import com.michelin.api.entity.Salesman;
import com.michelin.api.service.SvcAdmin;
import com.michelin.exception.ApiException;

@RestController
@RequestMapping("/michelin/admin")
public class CtrlAdmin {

    @Autowired
    SvcAdmin svc;

    /*
     * Client Section
     */
    @GetMapping("client/searchBy/{client_id}")
    public ResponseEntity<Client> searchClient(@PathVariable Integer client_id) {
        return new ResponseEntity<>(svc.getClientById(client_id), HttpStatus.OK);
    }

    /*
     * Salesman Section
     */

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerSalesman(@Valid @RequestBody SalesmanDto in, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseEntity<>(svc.registerSalesman(in), HttpStatus.OK);
    }

    @DeleteMapping("/salesman/delete/{salesman_id}")
    public ResponseEntity<ApiResponse> deleteSalesman(@PathVariable Integer salesman_id) {
        return new ResponseEntity<>(svc.deleteSalesman(salesman_id), HttpStatus.OK);
    }

    @GetMapping("/salesman/")
    public ResponseEntity<List<Salesman>> getAll() {
        return new ResponseEntity<>(svc.getAll(), HttpStatus.OK);
    }

    @GetMapping("/salesman/searchBy/{salesman_id}")
    public ResponseEntity<Salesman> searchSalesman(@PathVariable Integer salesman_id) {
        return new ResponseEntity<>(svc.getSalesmanById(salesman_id), HttpStatus.OK);
    }

    /*
     * Product Section
     */

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(svc.getProducts(), HttpStatus.OK);
    }

    @PostMapping("/products/add/{administrator_id}")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody ProductDto product, BindingResult bindingResult,
    @PathVariable Integer administrator_id) {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        return new ResponseEntity<>(svc.addProduct(product, administrator_id), HttpStatus.OK);
    }

    @PutMapping("/product/update/{product_id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer product_id, @RequestBody ProductDto product) {
        return new ResponseEntity<>(svc.updateProduct(product_id, product), HttpStatus.OK);
    }
    
    @DeleteMapping("/product/delete/{product_id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer product_id) {
        return new ResponseEntity<>(svc.deleteProduct(product_id), HttpStatus.OK);
    }

}

