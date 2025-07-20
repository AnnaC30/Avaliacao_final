package com.restaurante.pedido.service;

import com.restaurante.pedido_client.CardapioClient;
import com.restaurante.pedido_dto.PratoDTO;
import com.restaurante.pedido.model.Pedido;
import com.restaurante.pedido.repository.PedidoRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CardapioClient cardapioClient;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
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
