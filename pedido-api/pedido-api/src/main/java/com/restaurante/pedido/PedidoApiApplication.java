package com.restaurante.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.restaurante")
@EnableFeignClients(basePackages = "com.restaurante.pedido_client")
public class PedidoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoApiApplication.class, args);
    }
}
