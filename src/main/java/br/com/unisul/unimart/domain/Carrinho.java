package br.com.unisul.unimart.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Carrinho extends GenericDomain {
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private CondicaoPagamento condicaoPagamento;

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

}