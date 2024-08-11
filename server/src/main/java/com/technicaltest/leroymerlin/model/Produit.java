package com.technicaltest.leroymerlin.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class Produit {

    private Long id;

    @NotBlank(message = "The product name can not be empty!")
    private String nom;

    @NotNull(message = "The price is mandatory!")
    @Min(value = 1, message = "The minimum price is set to 1!")
    private Double prix;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}