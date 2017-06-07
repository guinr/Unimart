package br.com.unisul.unimart.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.ClienteDao;
import br.com.unisul.unimart.domain.Cliente;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean extends GenericBean {
	
	private Cliente cliente;
	private	List<Cliente> clienteList;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}
	
	@PostConstruct
	public void listar() {
		try {
			novo();
			ClienteDao clienteDao = new ClienteDao();
			clienteList = clienteDao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar cliente");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		cliente = new Cliente();
	}
	
	public void excluir(ActionEvent event) {
		try {
			cliente = (Cliente)event.getComponent().getAttributes().get("clienteExcluir");
			ClienteDao clienteDao = new ClienteDao();
			clienteDao.excluir(cliente);
			listar();
			Messages.addGlobalInfo(cliente.getNome() + " exluído com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir cliente");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent event) {
		cliente = (Cliente)event.getComponent().getAttributes().get("clienteAlterar");
	}

	public void salvar() {
		try {
			ClienteDao clienteDao = new ClienteDao();
			clienteDao.merge(cliente);
			Messages.addGlobalInfo("Cliente cadastrado com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar cliente");
			e.printStackTrace();
		}
	}
	
}