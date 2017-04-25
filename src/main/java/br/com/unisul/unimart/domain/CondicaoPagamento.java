package br.com.unisul.unimart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class CondicaoPagamento extends GenericDomain {

	@Column(length=45)
	private String nome;
	
	@Column()
	private Double indice;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getIndice() {
		return indice;
	}
	
	public void setIndice(Double indice) {
		this.indice = indice;
	}
	
}
