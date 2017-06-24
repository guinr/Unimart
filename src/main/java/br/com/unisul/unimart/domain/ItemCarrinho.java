package br.com.unisul.unimart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ItemCarrinho extends GenericDomain {

	@Column()
	private Double quantidade;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
}