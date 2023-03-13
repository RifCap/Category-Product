package com.product.cap.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class ProductDTO {
    private UUID Id;
    @Size(min = 2, max = 20, message = "Name must be between {min} and {max} characters")
    private String Nom;
    @Size(min = 2, max = 30, message = "Description must be between {min} and {max} characters")
    private String Description;
    private double Prix_vente;
    private double Prix_achat;
    private int Stock;
    private CategoryDTO categories;

    public ProductDTO() { }

    public ProductDTO(UUID id, String nom, String description, double prix_vente, double prix_achat, int stock, CategoryDTO categories) {
        Id = id;
        Nom = nom;
        Description = description;
        Prix_vente = prix_vente;
        Prix_achat = prix_achat;
        Stock = stock;
        this.categories = categories;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrix_vente() {
        return Prix_vente;
    }

    public void setPrix_vente(double prix_vente) {
        Prix_vente = prix_vente;
    }

    public double getPrix_achat() {
        return Prix_achat;
    }

    public void setPrix_achat(double prix_achat) {
        Prix_achat = prix_achat;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public CategoryDTO getCategories() {
        return categories;
    }

    public void setCategories(CategoryDTO categories) {
        this.categories = categories;
    }
}
