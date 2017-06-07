package br.com.unisul.unimart.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.StatusPedidoDao;
import br.com.unisul.unimart.domain.StatusPedido;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class StatusPedidoBean extends GenericBean {
	
	private StatusPedido statusPedido;
	private	List<StatusPedido> statusPedidoList;
	
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public List<StatusPedido> getStatusPedidoList() {
		return statusPedidoList;
	}

	public void setStatusPedidoList(List<StatusPedido> statusPedidoList) {
		this.statusPedidoList = statusPedidoList;
	}
	
	@PostConstruct
	public void listar() {
		try {
			StatusPedidoDao statusPedidoDao = new StatusPedidoDao();
			statusPedidoList = statusPedidoDao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar status de pedido");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		statusPedido = new StatusPedido();
	}
	
	public void excluir(ActionEvent event) {
		try {
			statusPedido = (StatusPedido)event.getComponent().getAttributes().get("statusPedidoExcluir");
			StatusPedidoDao statusPedidoDao = new StatusPedidoDao();
			statusPedidoDao.excluir(statusPedido);
			listar();
			Messages.addGlobalInfo(statusPedido.getNome() + " exluído com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir status de pedido");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent event) {
		statusPedido = (StatusPedido)event.getComponent().getAttributes().get("statusPedidoAlterar");
	}

	public void salvar() {
		try {
			StatusPedidoDao statusPedidoDao = new StatusPedidoDao();
			statusPedidoDao.merge(statusPedido);
			Messages.addGlobalInfo("Status de pedido cadastrado com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar status de pedido");
			e.printStackTrace();
		}
	}
	
}