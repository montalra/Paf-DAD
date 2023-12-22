package com.gateway.api;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemogatewayApplication {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
        		// microservices yanina
            .route("clientes_route", r -> r.path("/api/clientes/**")
                .uri("https://2fca-2800-200-f110-2823-f016-25b6-2425-3e8.ngrok-free.app"))
         // microservices jesus
            .route("compras_route", r -> r.path("/api/compras/**")
                .uri("https://5aec-38-56-223-8.ngrok-free.app"))
            
            // microservices willians
            .route("productos_route", r -> r.path("/api/productos/**")
                .uri("https://f3c8-2800-200-fcb0-c0-3040-709f-52c5-7dd9.ngrok-free.app"))
            
            
         // microservices sarahi
            .route("proveedores_route", r -> r.path("/api/proveedores/**")
                .uri("https://87f1-181-67-205-244.ngrok-free.app"))
            
         // microservices Rober
            .route("marcas_route", r -> r.path("/api/marcas/**")
                .uri("https://3597-132-184-129-169.ngrok-free.app"))
         
        
        
            .build();
    }
}
