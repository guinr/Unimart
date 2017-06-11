package br.com.unisul.unimart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.unisul.unimart.domain.Produto;
import br.com.unisul.unimart.domain.ProdutoCategoria;
import br.com.unisul.unimart.util.HibernateUtil;

public class ProdutoDao extends GenericDao<Produto> {
	
	@SuppressWarnings("unchecked")
	public List<Produto> listarPorCategoria(ProdutoCategoria cat) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Produto.class);
			consulta.add(Restrictions.eq("produtoCategoria.codigo", cat.getCodigo()));
			List<Produto> resultado = consulta.list();
			return resultado;
		} catch (Exception e) {
			throw(e);
		} finally {
			sessao.close();
		}
	}
}
