package com.technicaltest.leroymerlin.controller;

import com.technicaltest.leroymerlin.model.Produit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProduitController.class)
public class ProduitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProduits() throws Exception {
        // Test de l'appel GET /produits pour récupérer la liste des produits
        mockMvc.perform(MockMvcRequestBuilders.get("/produits")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testAddProduit() throws Exception {
        // Création d'un produit valide à ajouter
        Produit produit = new Produit();
        produit.setNom("Produit Test");
        produit.setPrix(10.0);

        // Test de l'appel POST /produits pour ajouter un produit
        mockMvc.perform(MockMvcRequestBuilders.post("/produits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"Produit Test\",\"prix\":10.0}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Produit Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prix").value(10.0));
    }

    @Test
    public void testAddProduitWithDuplicateName() throws Exception {
        // Création d'un produit initial à ajouter
        Produit produit = new Produit();
        produit.setNom("Produit Duplication");
        produit.setPrix(10.0);

        // Ajout du produit initial pour tester le duplicata
        mockMvc.perform(MockMvcRequestBuilders.post("/produits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"Produit Duplication\",\"prix\":10.0}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Création d'un produit avec le même nom pour tester le cas de duplication
        mockMvc.perform(MockMvcRequestBuilders.post("/produits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"Produit Duplication\",\"prix\":15.0}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Il existe déjà un produit avec ce nom!"));
    }
}