package com.michelin.api.service;

import java.util.List;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.entity.Product;

public interface SvcClient {
    public ApiResponse registerClient(ClientDto client);

    public ApiResponse updatePassword(PasswordDto password, Integer client_id);

    public List<Product> getAllProducts();

    public ApiResponse createOrder(Integer product_id, Integer client_id);
}
