package br.com.unisul.unimart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.unisul.unimart.domain.ItemCarrinho;
import br.com.unisul.unimart.util.HibernateUtil;

public class ItemCarrinhoDao extends GenericDao<ItemCarrinho> {
	
	@SuppressWarnings("unchecked")
	public List<ItemCarrinho> listarPorExemplo(ItemCarrinho itemCarrinho) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ItemCarrinho.class);
			if (itemCarrinho.getCarrinho() != null && itemCarrinho.getCarrinho().getCodigo() != null) {
				consulta.add(Restrictions.eq("carrinho.codigo", itemCarrinho.getCarrinho().getCodigo()));
			}
			if (itemCarrinho.getProduto() != null && itemCarrinho.getProduto().getCodigo() != null) {
				consulta.add(Restrictions.eq("produto.codigo", itemCarrinho.getProduto().getCodigo()));
			}
			List<ItemCarrinho> resultado = consulta.list();
			return resultado;
		} catch (Exception e) {
			throw(e);
		} finally {
			sessao.close();
		}
	}
	
}