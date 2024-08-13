package com.technicaltest.leroymerlin.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

/**
 * Représente un produit dans le système.
 */
public class Produit {

    /**
     * Identifiant unique du produit.
     */
    private Long id;

    /**
     * Nom du produit. Ne peut pas être vide.
     */
    @NotBlank(message = "Le nom du produit ne peut pas être vide!")
    private String nom;

    /**
     * Prix du produit. Ne peut pas être nul et doit être supérieur ou égal à 1.
     */
    @NotNull(message = "Le prix est obligatoire!")
    @Min(value = 1, message = "Le prix minimum est fixé à 1!")
    private Double prix;

    /**
     * Obtient l'identifiant unique du produit.
     *
     * @return l'identifiant unique du produit
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du produit.
     *
     * @param id l'identifiant unique du produit à définir
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient le nom du produit.
     *
     * @return le nom du produit
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du produit.
     *
     * @param nom le nom du produit à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prix du produit.
     *
     * @return le prix du produit
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * Définit le prix du produit.
     *
     * @param prix le prix du produit à définir
     */
    public void setPrix(Double prix) {
        this.prix = prix;
    }
}