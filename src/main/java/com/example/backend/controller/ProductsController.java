package com.example.backend.controller;

/*
Este código define um controlador REST para gerenciar usuários em uma aplicação Spring Boot.
Ele usa o Spring Data JPA para interagir com o banco de dados e expõe endpoints
para criar, recuperar, atualizar e excluir usuários.
*/

import com.example.backend.model.Products; // Importa a classe User que representa a entidade de usuário
import com.example.backend.repository.ProductsRepository; // Importa a interface UserRepository para acessar o banco de dados
import org.springframework.beans.factory.annotation.Autowired; // Injeta a dependência do UserRepository
import org.springframework.http.ResponseEntity; // Usado para construir respostas HTTP
import org.springframework.web.bind.annotation.*; // Importa anotações para definir endpoints REST

import java.util.List; // Usado para retornar listas de usuários
import java.util.Optional; // Usado para lidar com a possibilidade de um usuário não ser encontrado

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/products") // Define o prefixo da URL para todos os endpoints deste controlador
public class ProductsController {

    @Autowired // Injeta uma instância do UserRepository
    private ProductsRepository productsRepository;

    @GetMapping // Mapeia requisições GET para /api/users para obter todos os usuários
    public List<Products> getAllUsers() {
        return productsRepository.findAll(); // Retorna uma lista de todos os usuários do banco de dados
    }

    @GetMapping("/{id}") // Mapeia requisições GET para /api/users/{id} para obter um usuário pelo ID
    public ResponseEntity<Products> getUserById(@PathVariable Long id) { // @PathVariable extrai o ID da URL
        Optional<Products> products = productsRepository.findById(id); // Busca o usuário pelo ID
        return products.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); // Retorna o usuário se encontrado, ou um erro 404 se não
    }

    @PostMapping // Mapeia requisições POST para /api/users para criar um novo usuário
    public Products createUser(@RequestBody Products products) { // @RequestBody desserializa o JSON da requisição para um objeto User
        return productsRepository.save(products); // Salva o novo usuário no banco de dados
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para /api/users/{id} para atualizar um usuário
    public ResponseEntity<Products> updateUser(@PathVariable Long id, @RequestBody Products productsDetails) {
        Optional<Products> optionalUser = productsRepository.findById(id); // Busca o usuário pelo ID
        if (optionalUser.isPresent()) { // Verifica se o usuário existe
            Products products = optionalUser.get(); // Obtém o usuário do Optional
            products.setName(productsDetails.getName()); // Atualiza o nome do usuário
            products.setDescription(productsDetails.getDescription());
            products.setPrice(productsDetails.getPrice());
            products.setDateAtt(productsDetails.getDateAtt());
            return ResponseEntity.ok(productsRepository.save(products)); // Salva o usuário atualizado e retorna 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o usuário não existir
        }
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /api/users/{id} para excluir um usuário
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (productsRepository.existsById(id)) { // Verifica se o usuário existe
            productsRepository.deleteById(id); // Exclui o usuário do banco de dados
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o usuário não existir
        }
    }
}