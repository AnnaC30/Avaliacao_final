package com.restaurante.pizza_api.pizza_client;

import com.restaurante.pizza_api.pizza_dto.PratoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cardapio-api")
public interface CardapioClient {

    @GetMapping("/pratos/{id}")
    PratoDTO getPratoById(@PathVariable("id") Long id);
}
