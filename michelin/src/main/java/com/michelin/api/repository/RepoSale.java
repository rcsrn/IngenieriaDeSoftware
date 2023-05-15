package com.michelin.api.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.michelin.api.entity.Sale;

public interface RepoSale extends JpaRepository<Sale, Integer>{

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sale (total, salesman_id, sale_date) VALUES (:total, :salesman_id, :sale_date)", nativeQuery = true)
    Integer createSale(
        @Param("total") Integer total,
        @Param("salesman_id") Integer salesman_id,
        @Param("sale_date") Date sale_date
     );
}
