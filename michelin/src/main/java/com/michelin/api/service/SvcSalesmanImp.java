package com.michelin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.entity.Salesman;
import com.michelin.api.repository.RepoSalesman;
import com.michelin.exception.ApiException;

@Service
public class SvcSalesmanImp implements SvcSalesman {

    @Autowired
    RepoSalesman repo;

    @Override 
    public ApiResponse updatePassword(PasswordDto in, Integer salesman_id) {
        Salesman salesman = repo.findBySalesmanId(salesman_id);
        if (salesman == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El vendedor no existe");
        }
        repo.updatePassword(in.getNewPassword(), salesman_id);
        return new ApiResponse("Contraseña actualizada");
    }
}
