package br.com.unisul.unimart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {
	
	@Column(length=100)
	private String nome;
	@Column()
	private Double valor;
	@Column()
	private Double quantidadeEstoque;
	@Lob
	@Column()
	private byte[] imagem;
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProdutoCategoria produtoCategoria;
	
	private Integer quantidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	public ProdutoCategoria getProdutoCategoria() {
		return produtoCategoria;
	}

	public void setProdutoCategoria(ProdutoCategoria produtoCategoria) {
		this.produtoCategoria = produtoCategoria;
	}

	public Integer getQuantidade() {
		if (quantidade == null) quantidade = 1;
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
