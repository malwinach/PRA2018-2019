package com.pracownia.spring.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Product entity.
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refId", scope=Product.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String productId;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private ZonedDateTime bestBeforeDate;

    @ManyToMany
    @JoinColumn(name = "seller_id")
    private Set<Seller> seller = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "producer_id")
    Producer producer;

    //required by Hibernate
    public Product(){

    }

    public Product(String productId, String name, BigDecimal price, ZonedDateTime date) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.bestBeforeDate = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZonedDateTime getBestBeforeDate() {
        return bestBeforeDate;
    }

    public void setBestBeforeDate(ZonedDateTime bestBeforeDate) {
        this.bestBeforeDate = bestBeforeDate;
    }

    public Set<Seller> getSeller() {
        return seller;
    }

    public void setSeller(Set<Seller> seller) {
        this.seller = seller;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
