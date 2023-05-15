package com.michelin.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ProductDto;
import com.michelin.api.dto.SalesmanDto;
import com.michelin.api.entity.Administrator;
import com.michelin.api.entity.Client;
import com.michelin.api.entity.Product;
import com.michelin.api.entity.Salesman;
import com.michelin.api.repository.RepoAdministrator;
import com.michelin.api.repository.RepoClient;
import com.michelin.api.repository.RepoProduct;
import com.michelin.api.repository.RepoSalesman;
import com.michelin.exception.ApiException;

@Service
public class SvcAdminImp implements SvcAdmin {

    @Autowired
    RepoSalesman repo;

    @Autowired
    RepoProduct repoProduct;

    @Autowired
    RepoAdministrator repoAdmin;

    @Autowired
    RepoClient repoClient;

    /*
     * Client Section
     */
    @Override
     public Client getClientById(Integer client_id) {
        Client client = repoClient.findByClientId(client_id);
        if (client == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El cliente no existe");
        }
        return client;
     }

     /*
     * Salesman section
     */

    @Override
    public ApiResponse registerSalesman(SalesmanDto in) {
        Salesman salesman = repo.findByEmail(in.getEmail());

        if (salesman != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El correo ya esta registrado");
        }

        String password = generateNewPassword(10);
        try {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(in.getDateOfBirth());
        repo.createClient(in.getName(), in.getEmail(), password, date);
        } catch (ParseException pe) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Formato de fecha incorrecto");
        }
        return new ApiResponse("registro exitoso");
    }   

    private String generateNewPassword(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
    
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public ApiResponse deleteSalesman(Integer salesman_id) {
        Salesman salesman = repo.findBySalesmanId(salesman_id);

        if (salesman == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El vendedor no existe");
        }
        repo.deleteSalesman(salesman_id);
        return new ApiResponse("Vendedor eliminado");
     }

    public List<Salesman> getAll() {
        return repo.getAll();
    }

    public Salesman getSalesmanById(Integer salesman_id) {
        Salesman salesman = repo.findBySalesmanId(salesman_id);

        if (salesman == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El vendedor no existe");
        }

        return salesman;
    }

    /*
     * Product Section
     */

     @Override
     public List<Product> getProducts() {
        return repoProduct.getAllProducts();
     }

     @Override
     public ApiResponse addProduct(ProductDto product, Integer administrator_id) {
        Product productSaved = repoProduct.findByName(product.getName());

        if (productSaved != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El producto ya existe");
        }

        Administrator admin = repoAdmin.findByAdminId(administrator_id);

        if (admin == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El administrador no existe");
        }

        repoProduct.createProduct(product.getName(), product.getDescription(), product.getPrice(), administrator_id);
        return new ApiResponse("Producto agregado exitosamente");
     }
 
     @Override
     public ApiResponse updateProduct(Integer product_id, ProductDto product) {
        Product productSaved = repoProduct.findProductById(product_id);
        if (productSaved == null) { 
            throw new ApiException(HttpStatus.NOT_FOUND, "El producto no existe");
        }

        repoProduct.updateProduct(product_id, product.getName(), product.getDescription(), product.getPrice());
        return new ApiResponse("Producto actualizado exitosamente");
     }
 
     @Override
     public ApiResponse deleteProduct(Integer product_id) {
        Product product = repoProduct.findProductById(product_id);
        if (product == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El producto no existe");
        }

        repoProduct.deleteProductById(product_id);
        return new ApiResponse("Producto eliminado exitosamente");
     }
}   
