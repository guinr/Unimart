package br.com.unisul.unimart.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.unisul.unimart.dao.CarrinhoDao;
import br.com.unisul.unimart.domain.Carrinho;
import br.com.unisul.unimart.domain.ItemCarrinho;
import br.com.unisul.unimart.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
@ViewScoped
public class CarrinhoBean extends GenericBean {
	
	@ManagedProperty(value = "#{param.codigoCarrinho}")
    private String codigoCarrinho;
	
	public String getCodigoCarrinho() {
		return codigoCarrinho;
	}

	public void setCodigoCarrinho(String codigoCarrinho) {
		this.codigoCarrinho = codigoCarrinho;
	}

	private ItemCarrinhoBean itemCarrinhoBean;
	private Carrinho carrinho;
	private List<ItemCarrinho> itemCarrinhoList;
	
	@PostConstruct
	public void criaCarrinho() {
		setCarrinho(new Carrinho());
		CarrinhoDao carrinhoDao = new CarrinhoDao();
		setCarrinho(carrinhoDao.merge(getCarrinho()));
		itemCarrinhoBean = new ItemCarrinhoBean();
	}
	
	public void adicionaProduto(Produto produto) {
		if (carrinho == null) {
			criaCarrinho();
		}
		itemCarrinhoBean.adicionarItem(carrinho, produto);
		recarregaItensCarrinho();
	}
	
	private void recarregaItensCarrinho() {
		itemCarrinhoList = itemCarrinhoBean.getItemCarrinhoList(carrinho);
	}
	
	//Get/Sets
	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public ItemCarrinhoBean getItemCarrinhoBean() {
		return itemCarrinhoBean;
	}

	public void setItemCarrinhoBean(ItemCarrinhoBean itemCarrinhoBean) {
		this.itemCarrinhoBean = itemCarrinhoBean;
	}

	public List<ItemCarrinho> getItemCarrinhoList() {
		return itemCarrinhoList;
	}

	public void setItemCarrinhoList(List<ItemCarrinho> itemCarrinhoList) {
		this.itemCarrinhoList = itemCarrinhoList;
	}
	
}