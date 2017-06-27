package br.com.unisul.unimart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ItemCarrinho extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Carrinho carrinho;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	@Column()
	private Integer quantidade;
	
	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getCarrinho().getCodigo() == ((ItemCarrinho)obj).getCarrinho().getCodigo()
				&& this.getProduto().getCodigo() == ((ItemCarrinho)obj).getProduto().getCodigo();
	}
	
}