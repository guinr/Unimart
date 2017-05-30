package br.com.unisul.unimart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.unisul.unimart.domain.Cliente;
import br.com.unisul.unimart.util.HibernateUtil;

public class ClienteDao extends GenericDao<Cliente> {
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listarPorEmail(String email){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			consulta.add(Restrictions.eq("email", email));
			List<Cliente> resultado = consulta.list();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Cliente listarPorEmailSenha(String email, String senha){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			consulta.add(Restrictions.eq("email", email));
			consulta.add(Restrictions.eq("senha", senha));
				List<Cliente> lista = consulta.list();
			if (lista.size() > 0) {
				Cliente resultado = lista.get(0);
				return resultado;
			}
			else{
				return null;
			}
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}
	
}
