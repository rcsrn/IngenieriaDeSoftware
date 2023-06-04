package com.michelin.api.service;

import java.util.List;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.LoginDto;
import com.michelin.api.dto.ProductDto;
import com.michelin.api.dto.SalesmanDto;
import com.michelin.api.entity.Client;
import com.michelin.api.entity.Product;
import com.michelin.api.entity.Salesman;

public interface SvcAdmin {

    /*
     * Admin Section
     */


     public ApiResponse loginAdmin(LoginDto in);  


     /*
     * Client Section
     */

    public Client getClientById(Integer client_id);

     /*
     * Salesman Section
     */

    public ApiResponse registerSalesman(SalesmanDto in);   

    public ApiResponse deleteSalesman(Integer salesman_id);

    public List<Salesman> getAll();

    public Salesman getSalesmanById(Integer salesman_id);

    /*
     * Product Section
     */

    public List<Product> getProducts();

    public ApiResponse addProduct(ProductDto product, Integer administrator_id);

    public ApiResponse updateProduct(Integer product_id, ProductDto product);

    public ApiResponse deleteProduct(Integer product_id);

}
