package com.generation.daterrapramesa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.daterrapramesa.model.Produto;
import com.generation.daterrapramesa.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos () {
		return ResponseEntity.ok(repository.findAll());
	} 
}
