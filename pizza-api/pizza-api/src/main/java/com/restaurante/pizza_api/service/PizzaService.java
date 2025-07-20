package com.restaurante.pizza_api.service;

import com.restaurante.pizza_api.pizza_client.CardapioClient;
import com.restaurante.pizza_api.pizza_dto.PratoDTO;
import com.restaurante.pizza_api.model.Pizza;
import com.restaurante.pizza_api.repository.PizzaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private CardapioClient cardapioClient;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findById(Long id) {
        return pizzaRepository.findById(id);
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deleteById(Long id) {
        pizzaRepository.deleteById(id);
    }

    @CircuitBreaker(name = "cardapioService", fallbackMethod = "fallbackPrato")
    public PratoDTO getPratoFromCardapio(Long pratoId) {
        return cardapioClient.getPratoById(pratoId);
    }

    public PratoDTO fallbackPrato(Long pratoId, Throwable t) {
        PratoDTO dto = new PratoDTO();
        dto.setId(pratoId);
        dto.setNome("Prato indisponível");
        dto.setDescricao("Não foi possível recuperar o prato no momento");
        dto.setPreco(0.0);
        return dto;
    }
}
