package com.michelin.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.michelin.api.entity.Order;

public interface RepoOrder extends JpaRepository<Order, Integer>{
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product_order (status, sale_id, client_id) VALUES (:status, :sale_id, :client_id)", nativeQuery = true)
    void createSale(
        @Param("status") Integer status,
        @Param("sale_id") Integer sale_id,
        @Param("client_id") Integer client_id
     );
}
