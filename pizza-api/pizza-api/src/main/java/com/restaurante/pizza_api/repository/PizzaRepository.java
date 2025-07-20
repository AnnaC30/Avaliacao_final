package com.restaurante.pizza_api.repository;

import com.restaurante.pizza_api.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
