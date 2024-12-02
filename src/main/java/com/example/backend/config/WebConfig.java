package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica que esta classe é uma classe de configuração do Spring
public class WebConfig implements WebMvcConfigurer { // Implementa a interface para configurar o Spring MVC

    @Override // Sobrescreve o método para adicionar mapeamentos CORS
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Mapeia todas as requisições que começam com /api
                .allowedOrigins("http://localhost:5173") // Permite o frontend local (porta 5173)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite os métodos HTTP especificados
                .allowCredentials(true); // Permite enviar cookies e cabeçalhos de autenticação
    }
}