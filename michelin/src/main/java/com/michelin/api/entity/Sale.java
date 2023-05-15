package com.michelin.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @Column(name = "sale_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sale_id;

    @Column(name = "total")
    @NotNull
    private Integer total;

    @OneToOne
    @JoinColumn(name = "salesman_id")
    private Salesman salesman;

    @Column(name = "sale_date")
    private Date sale_date;

    public Integer getSaleId() {
        return sale_id;
    }

    public void setSaleId(Integer sale_id) {
        this.sale_id = sale_id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public Date getSaleDate() {
        return sale_date;
    }

    public void setSaleDate(Date sale_date) {
        this.sale_date = sale_date;
    }


}
