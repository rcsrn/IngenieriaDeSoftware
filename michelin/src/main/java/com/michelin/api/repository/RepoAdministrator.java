package com.michelin.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.michelin.api.entity.Administrator;

public interface RepoAdministrator extends JpaRepository<Administrator,Integer> {
    
    @Transactional
    @Query(value = "SELECT * FROM administrator WHERE administrator_id = :administrator_id", nativeQuery = true)
    Administrator findByAdminId(@Param("administrator_id") Integer administrator_id);

}
