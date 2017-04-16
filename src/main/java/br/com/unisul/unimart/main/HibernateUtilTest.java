package br.com.unisul.unimart.main;

import org.hibernate.Session;

import br.com.unisul.unimart.util.HibernateUtil;

public class HibernateUtilTest {

	public static void main(String args[]) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
	
}
