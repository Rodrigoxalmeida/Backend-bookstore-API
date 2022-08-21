package br.com.bookstore.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Livro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@NotEmpty(message="Campo TITULO Requerido")
	@Length(min= 3, max= 50, message="Campo TITULO deve ter entre 3 e 50 caracteres")
	private String titulo;
	@NotEmpty(message="Campo NOME_AUTOR Requerido")
	@Length(min= 3, max= 50, message="Campo NOME_AUTOR deve ter entre 3 e 50 caracteres")
	private String nome_autor;
	@NotNull(message="Campo PRECO_CAPA requerido")
	private Double preco_capa;
	@NotEmpty(message="Campo TEXTO Requerido")
	@Length(min= 10, max= 2000000, message="Campo TEXTO deve ter entre 3 e 2.000.000 caracteres")
	private String texto;

	// UM LIVRO TEM QUE TER UMA CATEGORIA
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;

	public Livro() {

	}

	public Livro(Integer id, String titulo, String nome_autor, String texto, Double preco_capa,Categoria categoria) {
		this.id = id;
		this.titulo = titulo;
		this.nome_autor = nome_autor;
		this.texto = texto;
		this.preco_capa = preco_capa;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNome_autor() {
		return nome_autor;
	}

	public void setNome_autor(String nome_autor) {
		this.nome_autor = nome_autor;
	}
	
	public Double getPreco_capa() {
		return preco_capa;
	}

	public void setPreco_capa(Double preco_capa) {
		this.preco_capa = preco_capa;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}

}
