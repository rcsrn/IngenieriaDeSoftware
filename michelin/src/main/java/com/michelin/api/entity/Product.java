package com.michelin.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @JsonProperty("name")
    @NotNull
    @Column(name = "name")
    private String name;

    @JsonProperty("description")
    @NotNull
    @Column(name = "description")
    private String description;

    @JsonProperty("price")
    @NotNull
    @Column(name = "price")
    private Integer price;

    @OneToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;


    public Integer getProductId() {
        return product_id;
    }
    
    public void setProductId(Integer product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    

}
