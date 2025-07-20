package com.restaurante.cardapio_api.service;

import com.restaurante.cardapio_api.model.Prato;
import com.restaurante.cardapio_api.repository.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PratoService {

    @Autowired
    private PratoRepository pratoRepository;

    public List<Prato> listarTodos() {
        return pratoRepository.findAll();
    }

    public Optional<Prato> buscarPorId(Long id) {
        return pratoRepository.findById(id);
    }

    public Prato salvar(Prato prato) {
        return pratoRepository.save(prato);
    }

    public void deletar(Long id) {
        pratoRepository.deleteById(id);
    }

    public Prato atualizar(Long id, Prato novoPrato) {
        return pratoRepository.findById(id)
                .map(prato -> {
                    prato.setNome(novoPrato.getNome());
                    prato.setDescricao(novoPrato.getDescricao());
                    prato.setPreco(novoPrato.getPreco());
                    return pratoRepository.save(prato);
                })
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));
    }
}
