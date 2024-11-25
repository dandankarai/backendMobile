package com.example.backend.repository;

import com.example.backend.model.Products;
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository do Spring Data JPA
import org.springframework.stereotype.Repository; // Anotação para indicar que esta interface é um repositório

@Repository // Indica que esta interface é um repositório Spring
public interface ProductsRepository extends JpaRepository<Products, Long> {
    // Estende JpaRepository, especificando a entidade (User) e o tipo da chave primária (Long)
}