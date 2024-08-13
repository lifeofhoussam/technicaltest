package com.technicaltest.leroymerlin.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProduitTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        // Initialisation du validateur avant chaque test
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldNotValidateProduitWithEmptyNom() {
        // Création d'un produit avec un nom vide
        Produit produit = new Produit();
        produit.setNom("");
        produit.setPrix(10.0);

        // Validation du produit et vérification qu'il y a une violation
        Set<ConstraintViolation<Produit>> violations = validator.validate(produit);

        // Vérifie qu'il y a une violation et que le message correspond
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Le nom du produit ne peut pas être vide!");
    }

    @Test
    public void shouldNotValidateProduitWithNullPrix() {
        // Création d'un produit avec un prix nul
        Produit produit = new Produit();
        produit.setNom("Test Product");
        produit.setPrix(null);

        // Validation du produit et vérification qu'il y a une violation
        Set<ConstraintViolation<Produit>> violations = validator.validate(produit);

        // Vérifie qu'il y a une violation et que le message correspond
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Le prix est obligatoire!");
    }

    @Test
    public void shouldNotValidateProduitWithPrixBelowMinimum() {
        // Création d'un produit avec un prix inférieur au minimum requis
        Produit produit = new Produit();
        produit.setNom("Test Product");
        produit.setPrix(0.5);

        // Validation du produit et vérification qu'il y a une violation
        Set<ConstraintViolation<Produit>> violations = validator.validate(produit);

        // Vérifie qu'il y a une violation et que le message correspond
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Le prix minimum est fixé à 1!");
    }

    @Test
    public void shouldValidateValidProduit() {
        // Création d'un produit valide
        Produit produit = new Produit();
        produit.setNom("Test Product");
        produit.setPrix(10.0);

        // Validation du produit et vérification qu'il n'y a pas de violation
        Set<ConstraintViolation<Produit>> violations = validator.validate(produit);

        // Vérifie qu'il n'y a aucune violation
        assertThat(violations).isEmpty();
    }
}