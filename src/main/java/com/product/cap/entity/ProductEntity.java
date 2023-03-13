package com.product.cap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true)
    private UUID Id;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String Nom;

    @Column(columnDefinition ="varchar(30)", nullable = false)
    private String Description;

    @Column(nullable = false)
    private double Prix_vente;

    @Column(nullable = false)
    private double Prix_achat;

    @Column(nullable = false)
    private int Stock;

    @ManyToOne
    private CategoryEntity categories;

    public ProductEntity() {
    }

    public ProductEntity(UUID id, String nom, String description, double prix_vente, double prix_achat, int stock, CategoryEntity categories) {
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

    public CategoryEntity getCategories() {
        return categories;
    }

    public void setCategories(CategoryEntity categories) {
        this.categories = categories;
    }
}
