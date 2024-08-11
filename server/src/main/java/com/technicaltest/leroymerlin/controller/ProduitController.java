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

@RestController
@RequestMapping("/produits")
public class ProduitController {

    private final List<Produit> produits = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public List<Produit> getProduits() {
        return produits.stream()
                .sorted(Comparator.comparing(Produit::getNom))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Object> addProduit(@Valid @RequestBody Produit produit) {
        boolean nameExists = produits.stream()
                .anyMatch(p -> p.getNom().equalsIgnoreCase(produit.getNom()));

        if (nameExists) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "There is already a product with this name!");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        produit.setId(counter.incrementAndGet());
        produits.add(produit);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }
}

