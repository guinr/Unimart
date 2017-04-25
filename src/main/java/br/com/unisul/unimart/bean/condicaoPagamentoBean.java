package br.com.unisul.unimart.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.CondicaoPagamentoDao;
import br.com.unisul.unimart.domain.CondicaoPagamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class condicaoPagamentoBean implements Serializable {
	
	private CondicaoPagamento condicaoPagamento;
	private	List<CondicaoPagamento> condicaoPagamentoList;
	
	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public List<CondicaoPagamento> getCondicaoPagamentoList() {
		return condicaoPagamentoList;
	}

	public void setCondicaoPagamentoList(List<CondicaoPagamento> condicaoPagamentoList) {
		this.condicaoPagamentoList = condicaoPagamentoList;
	}
	
	@PostConstruct
	public void listar() {
		try {
			CondicaoPagamentoDao CondicaoPagamentoDao = new CondicaoPagamentoDao();
			condicaoPagamentoList = CondicaoPagamentoDao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar condicao de pagamento");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		condicaoPagamento = new CondicaoPagamento();
	}
	
	public void excluir(ActionEvent event) {
		try {
			condicaoPagamento = (CondicaoPagamento)event.getComponent().getAttributes().get("condicaoPagamentoExcluir");
			CondicaoPagamentoDao CondicaoPagamentoDao = new CondicaoPagamentoDao();
			CondicaoPagamentoDao.excluir(condicaoPagamento);
			listar();
			Messages.addGlobalInfo(condicaoPagamento.getNome() + " exluído com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir condicao de pagamento");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent event) {
		condicaoPagamento = (CondicaoPagamento)event.getComponent().getAttributes().get("condicaoPagamentoAlterar");
	}

	public void salvar() {
		try {
			CondicaoPagamentoDao CondicaoPagamentoDao = new CondicaoPagamentoDao();
			CondicaoPagamentoDao.merge(condicaoPagamento);
			Messages.addGlobalInfo("Condicao de pagamento cadastrado com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar condicao de pagamento");
			e.printStackTrace();
		}
	}
	
}