package br.com.unisul.unimart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class ItemPedido extends GenericDomain {
	
	@Column()
	private Double quantidadeVendida;

	public Double getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Double quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

}
