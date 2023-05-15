package com.michelin.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.michelin.api.entity.Product;

public interface RepoProduct extends JpaRepository<Product,Integer>{
    
    @Transactional
    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> getAllProducts();

    @Transactional
    @Query(value = "SELECT * FROM product where name = :name", nativeQuery = true)
    Product findByName(@Param("name") String name);

    @Transactional
    @Query(value = "SELECT * FROM product where product_id = :product_id", nativeQuery = true)
    Product findProductById(@Param("product_id") Integer product_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product where product_id = :product_id", nativeQuery = true)
    void deleteProductById(@Param("product_id") Integer product_id);
   
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product (name, description, price, administrator_id) VALUES (:name, :description, :price, :administrator_id)", nativeQuery = true)
    void createProduct(
    @Param("name") String name,
    @Param("description") String description,
    @Param("price") Integer price,
    @Param("administrator_id") Integer administrator_id);

    @Modifying
    @Transactional
    @Query(value= "UPDATE product SET name = :name, description =:description, price =:price WHERE product_id = :product_id", nativeQuery = true)
    Integer updateProduct(
    @Param("product_id") Integer product_id, 
    @Param("name") String name, 
    @Param("description") String description, 
    @Param("price") Integer price);
}
