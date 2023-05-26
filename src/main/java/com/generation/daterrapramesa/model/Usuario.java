package com.generation.daterrapramesa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Campo obrigatório.")
	@Size(min = 3, max = 100, message = "Este campo deve conter no mínimo 3 caracteres e no máximo 100 caracteres. .")
	private String nome;

	@NotBlank
	@Column(unique = true)
	private String email;

	@Size(min = 3, max = 100, message = "Este campo deve conter no mínimo 3 caracteres e no máximo 100 caracteres. .")
	@NotBlank(message = "Campo obrigatório.")
	private String senha;

	private String foto;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario",  cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produtos;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}
