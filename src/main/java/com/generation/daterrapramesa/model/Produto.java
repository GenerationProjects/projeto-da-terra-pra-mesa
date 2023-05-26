package com.generation.daterrapramesa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	@Size(min = 3, max = 100, message = "Este campo deve conter no mínimo 3 caracteres e no máximo 100 caracteres")
	private String nome;
	
	@Positive(message = "Campo não pode ter valor negativo")
	private Double preco;
	
	@NotBlank(message = "Campo obrigatório")
	@Size(min = 10, max = 255, message = "Este campo deve conter no mínimo 10 caracteres e no máximo 255 caracteres")
	private String descricao;
	
	@Positive(message = "Campo não pode ter valor negativo")
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
