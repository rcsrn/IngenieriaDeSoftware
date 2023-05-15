package com.michelin.api.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.michelin.api.entity.Salesman;

public interface RepoSalesman extends JpaRepository<Salesman, Integer> {
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO salesman (name, email, password, date_of_birth) VALUES (:name, :email, :password, :date_of_birth)", nativeQuery = true)
    void createClient(
    @Param("name") String name,
    @Param("email") String email,
    @Param("password") String password,
    @Param("date_of_birth") Date date_of_birth);

    @Transactional
    @Query(value = "SELECT * FROM salesman WHERE email = :email", nativeQuery = true)
    Salesman findByEmail(@Param("email") String email);

    @Transactional
    @Query(value = "SELECT * FROM salesman WHERE salesman_id = :salesman_id", nativeQuery = true)
    Salesman findBySalesmanId(@Param("salesman_id") Integer salesman_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE salesman SET password = :password WHERE salesman_id = :salesman_id", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("salesman_id") Integer salesman_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM salesman WHERE salesman_id = :salesman_id", nativeQuery = true)
    void deleteSalesman(@Param("salesman_id") Integer salesman_id);

    @Transactional
    @Query(value = "SELECT * FROM salesman", nativeQuery = true)
    List<Salesman> getAll();
}
