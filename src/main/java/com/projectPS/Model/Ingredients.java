package com.projectPS.Model;

import java.time.LocalDate;

public class Ingredients {
    private Long id;
    private String name;
    private LocalDate expirationDate;
    private Integer quantity;

    public Ingredients() {
    }

    public Ingredients(Long id, String name, LocalDate expirationDate, Integer quantity) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public Ingredients(String name, LocalDate expirationDate, Integer quantity) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                ", quantity=" + quantity +
                '}';
    }
}
