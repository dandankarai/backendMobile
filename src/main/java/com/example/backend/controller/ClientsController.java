package com.example.backend.controller;

import com.example.backend.model.Clients; // Importa a classe User que representa a entidade de usuário
import com.example.backend.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired; // Injeta a dependência do UserRepository
import org.springframework.http.ResponseEntity; // Usado para construir respostas HTTP
import org.springframework.web.bind.annotation.*; // Importa anotações para definir endpoints REST

import java.util.List; // Usado para retornar listas de usuários
import java.util.Optional; // Usado para lidar com a possibilidade de um usuário não ser encontrado

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/clients") // Define o prefixo da URL para todos os endpoints deste controlador
public class ClientsController {

    @Autowired // Injeta uma instância do UserRepository
    private ClientsRepository clientsRepository;

    @GetMapping // Mapeia requisições GET para /api/users para obter todos os usuários
    public List<Clients> getAllUsers() {
        return clientsRepository.findAll(); // Retorna uma lista de todos os usuários do banco de dados
    }

    @GetMapping("/{id}") // Mapeia requisições GET para /api/users/{id} para obter um usuário pelo ID
    public ResponseEntity<Clients> getUserById(@PathVariable Long id) { // @PathVariable extrai o ID da URL
        Optional<Clients> products = clientsRepository.findById(id); // Busca o usuário pelo ID
        return products.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); // Retorna o usuário se encontrado, ou um erro 404 se não
    }

    @PostMapping // Mapeia requisições POST para /api/users para criar um novo usuário
    public Clients createUser(@RequestBody Clients products) { // @RequestBody desserializa o JSON da requisição para um objeto User
        return clientsRepository.save(products); // Salva o novo usuário no banco de dados
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para /api/users/{id} para atualizar um usuário
    public ResponseEntity<Clients> updateUser(@PathVariable Long id, @RequestBody Clients productsDetails) {
        Optional<Clients> optionalUser = clientsRepository.findById(id); // Busca o usuário pelo ID
        if (optionalUser.isPresent()) { // Verifica se o usuário existe
            Clients clients = optionalUser.get(); // Obtém o usuário do Optional
            clients.setName(productsDetails.getName()); // Atualiza o nome do usuário
            clients.setLastName(productsDetails.getLastName());
            clients.setEmail(productsDetails.getEmail());
            clients.setAge(productsDetails.getAge());
            clients.setPhoto(productsDetails.getPhoto());
            return ResponseEntity.ok(clientsRepository.save(clients)); // Salva o usuário atualizado e retorna 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o usuário não existir
        }
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /api/users/{id} para excluir um usuário
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (clientsRepository.existsById(id)) { // Verifica se o usuário existe
            clientsRepository.deleteById(id); // Exclui o usuário do banco de dados
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o usuário não existir
        }
    }
}