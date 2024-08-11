package com.technicaltest.leroymerlin.controller;

import com.technicaltest.leroymerlin.model.Produit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    public ResponseEntity<Produit> addProduit(@RequestBody Produit produit) {
        produit.setId(counter.incrementAndGet());
        produits.add(produit);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }
}

