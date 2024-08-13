package com.technicaltest.leroymerlin.controller;

import com.technicaltest.leroymerlin.model.Produit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour gérer les opérations liées aux produits.
 */
@RestController
@RequestMapping("/produits")
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController {

    /**
     * Liste des produits stockés en mémoire.
     */
    private final List<Produit> produits = new ArrayList<>();

    /**
     * Compteur pour générer des ID uniques pour les produits.
     */
    private final AtomicLong counter = new AtomicLong();

    /**
     * Obtient la liste des produits triée par ordre alphabétique.
     *
     * @return la liste des produits triés
     */
    @GetMapping
    public List<Produit> getProduits() {
        return produits.stream()
                .sorted(Comparator.comparing(Produit::getNom)) // Trie les produits par nom
                .collect(Collectors.toList()); // Collecte les produits triés dans une liste
    }

    /**
     * Ajoute un nouveau produit à la liste des produits.
     *
     * @param produit le produit à ajouter
     * @return une réponse HTTP contenant le produit ajouté ou une erreur si le nom du produit existe déjà
     */
    @PostMapping
    public ResponseEntity<Object> addProduit(@Valid @RequestBody Produit produit) {
        // Vérifie si un produit avec le même nom existe déjà
        boolean nameExists = produits.stream()
                .anyMatch(p -> p.getNom().trim().equalsIgnoreCase(produit.getNom().trim()));

        // Si un produit avec ce nom existe déjà, retourne une erreur
        if (nameExists) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Il existe déjà un produit avec ce nom!");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); // Retourne une réponse avec un code 400 Bad Request
        }

        // Si le nom du produit est unique, attribue un nouvel ID et ajoute le produit à la liste
        produit.setId(counter.incrementAndGet()); // Génère un nouvel ID unique
        produits.add(produit); // Ajoute le produit à la liste
        return new ResponseEntity<>(produit, HttpStatus.CREATED); // Retourne une réponse avec le produit ajouté et un code 201 Created
    }
}