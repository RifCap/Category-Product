package com.product.cap.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


public class CategoryDTO {
    private UUID Id;
    @Size(min = 2, max = 20, message = "Name must be between {min} and {max} characters")
    private String Nom;
    @Size(min = 2, max = 30, message = "Description must be between {min} and {max} characters")
    private String Description;
    public CategoryDTO() { }
    public CategoryDTO(UUID id, String nom, String description, List<ProductDTO> products) {
        this.Id = id;
        this.Nom = nom;
        this.Description = description;
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

}
