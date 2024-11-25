package com.example.backend.model;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Atributo que armazena o ID do usuário

    @Column(nullable = false) // Define que o atributo "name" não pode ser nulo
    private String name; // Atributo que armazena o nome do usuário

    @Column(nullable = false, unique = true) // Define que o atributo "email" não pode ser nulo e deve ser único
    private String description; // Atributo que armazena o email do usuário

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String dateAtt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDateAtt() {
        return dateAtt;
    }

    public void setDateAtt(String dateAtt) {
        this.dateAtt = dateAtt;
    }
}