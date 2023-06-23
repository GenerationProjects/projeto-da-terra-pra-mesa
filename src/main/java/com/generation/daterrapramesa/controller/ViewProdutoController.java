package com.generation.daterrapramesa.controller;

import com.generation.daterrapramesa.model.Produto;
import com.generation.daterrapramesa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/view-produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViewProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id).map(res -> ResponseEntity.ok(res))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }
}
