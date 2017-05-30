package br.com.unisul.unimart.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.ClienteDao;
import br.com.unisul.unimart.domain.Cliente;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped	
public class LoginBean implements Serializable {
	
	
	private Cliente clienteLogado;
	private Cliente cliente;
	
	public Cliente getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(Cliente clienteLogado) {
		this.clienteLogado = clienteLogado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void novo(){
		cliente = new Cliente();
	}
	
	public void cancelar(){
		cliente = null;
	}
	
	public String doLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		HttpSession sess = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		ClienteDao c = new ClienteDao();
		clienteLogado = c.listarPorEmailSenha(cliente.getEmail(), cliente.getSenha());
		
		if (clienteLogado == null) {
			FacesContext.getCurrentInstance().validationFailed();
			Messages.addGlobalError("Erro ao efetuar login");
			return sess.getAttribute("currentPage").toString();
		}
		
		sess.setAttribute("user", clienteLogado.getNome());
		System.out.println(sess.getAttribute("user").toString());
		sess.setAttribute("admin", clienteLogado.getAdmin());
		Messages.addGlobalInfo("Login efetuado com sucesso");
		return sess.getAttribute("lastPage").toString();

	}

}
