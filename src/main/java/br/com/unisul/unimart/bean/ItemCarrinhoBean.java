package br.com.unisul.unimart.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.ItemCarrinhoDao;
import br.com.unisul.unimart.domain.Carrinho;
import br.com.unisul.unimart.domain.ItemCarrinho;
import br.com.unisul.unimart.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemCarrinhoBean extends GenericBean {

	public void adicionarItem(Carrinho carrinho, Produto produto) {
		try {
			ItemCarrinho itemCarrinho = new ItemCarrinho();
			itemCarrinho.setCarrinho(carrinho);
			itemCarrinho.setProduto(produto);
			itemCarrinho.setQuantidade(produto.getQuantidade());
			ItemCarrinhoDao itemCarrinhoDao = new ItemCarrinhoDao();
			List<ItemCarrinho> itemCarrinhoList = itemCarrinhoDao.listarPorExemplo(itemCarrinho);
			if (itemCarrinhoList.size() == 0) {
				itemCarrinhoDao.merge(itemCarrinho);
			} else {
				Integer index = null;
				index = itemCarrinhoList.indexOf(itemCarrinho);
				if (index != -1) {
					Integer codigo = itemCarrinhoList.get(index).getCodigo();
					itemCarrinho.setCodigo(codigo);
					itemCarrinhoDao.atualizar(itemCarrinho);
				}
			}
			Messages.addGlobalInfo("Produto " + produto.getNome() + " foi adicionado ao carrinho");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar itemCarrinho");
			e.printStackTrace();
		}
	}
	
	public List<ItemCarrinho> getItemCarrinhoList(Carrinho carrinho) {
		ItemCarrinho itemCarrinhoFilter = new ItemCarrinho();
		itemCarrinhoFilter.setCarrinho(carrinho);
		ItemCarrinhoDao itemCarrinhoDao = new ItemCarrinhoDao();
		return itemCarrinhoDao.listarPorExemplo(itemCarrinhoFilter);
	}
	
}