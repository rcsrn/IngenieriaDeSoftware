package com.michelin.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;  


@Entity
@Table(name = "product_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id")
    private Integer product_order_id;

    @Column(name = "status")
    private Integer status;

    @OneToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Integer getProductOrderId() {
        return product_order_id;
    }

    public void setProductOrderId(Integer product_order_id) {
        this.product_order_id = product_order_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
