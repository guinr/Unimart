package br.com.unisul.unimart.domain;

import java.util.List;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Carrinho extends GenericDomain {
	
	private List<ItemCarrinho> itemCarrinhoList;

	public List<ItemCarrinho> getItemCarrinhoList() {
		return itemCarrinhoList;
	}

	public void setItemCarrinhoList(List<ItemCarrinho> itemCarrinhoList) {
		this.itemCarrinhoList = itemCarrinhoList;
	}

}