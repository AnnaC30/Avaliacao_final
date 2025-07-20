package com.restaurante.pedido.controller;

import com.restaurante.pedido_dto.PratoDTO;
import com.restaurante.pedido.model.Pedido;
import com.restaurante.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.findById(id);
        return pedido.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedidoDetalhes) {
        Optional<Pedido> pedidoOptional = pedidoService.findById(id);

        if (!pedidoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Pedido pedido = pedidoOptional.get();
        pedido.setPratoId(pedidoDetalhes.getPratoId());
        pedido.setQuantidade(pedidoDetalhes.getQuantidade());
        pedido.setPrecoTotal(pedidoDetalhes.getPrecoTotal());

        Pedido atualizado = pedidoService.save(pedido);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/prato")
    public ResponseEntity<PratoDTO> getPratoDoPedido(@PathVariable Long id) {
        Optional<Pedido> pedidoOpt = pedidoService.findById(id);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PratoDTO prato = pedidoService.getPratoFromCardapio(pedidoOpt.get().getPratoId());
        return ResponseEntity.ok(prato);
    }
}
