package com.restaurante.pizza_api.controller;

import com.restaurante.pizza_api.model.Pizza;
import com.restaurante.pizza_api.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaService.findById(id);
        return pizza.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pizza createPizza(@RequestBody Pizza pizza) {
        return pizzaService.save(pizza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @RequestBody Pizza pizzaDetails) {
        Optional<Pizza> pizzaOptional = pizzaService.findById(id);

        if (!pizzaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Pizza pizza = pizzaOptional.get();
        pizza.setNome(pizzaDetails.getNome());
        pizza.setDescricao(pizzaDetails.getDescricao());
        pizza.setPreco(pizzaDetails.getPreco());

        Pizza atualizado = pizzaService.save(pizza);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        pizzaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
