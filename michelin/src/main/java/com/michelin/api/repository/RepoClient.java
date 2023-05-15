package com.michelin.api.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.michelin.api.entity.Client;


public interface RepoClient extends JpaRepository<Client,Integer>{

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO client (name, email, password, date_of_birth) VALUES (:name, :email, :password, :date_of_birth)", nativeQuery = true)
    void createClient(
    @Param("name") String name,
    @Param("email") String email,
    @Param("password") String password,
    @Param("date_of_birth") Date date_of_birth);

    @Transactional
    @Query(value = "SELECT * FROM client WHERE email = :email", nativeQuery = true)
    Client findByEmail(@Param("email") String email);

    @Transactional
    @Query(value = "SELECT * FROM client WHERE client_id = :client_id", nativeQuery = true)
    Client findByClientId(@Param("client_id") Integer client_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE client SET password = :password WHERE client_id = :client_id", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("client_id") Integer client_id);

    
}
