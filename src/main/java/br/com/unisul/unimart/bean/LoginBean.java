package br.com.unisul.unimart.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.ClienteDao;
import br.com.unisul.unimart.domain.Cliente;
import br.com.unisul.unimart.util.SessionContext;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private Cliente clienteLogado;
	private Cliente cliente;
	private Boolean logado;

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

	public Boolean getLogado() {
		return logado;
	}

	public void setLogado(Boolean logado) {
		this.logado = logado;
	}

	public void novo() {
		cliente = new Cliente();
	}

	public void cancelar() {
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
			return "";
		}

		sess.setAttribute("user", clienteLogado);
		Cliente user = (Cliente) sess.getAttribute("user");
		sess.setAttribute("adm", (Boolean) clienteLogado.getAdmin());
		System.out.println(user.getNome()+" Logado");
		setLogado(true);
		Messages.addGlobalInfo("Login efetuado com sucesso");
		return sess.getAttribute("currentPage").toString() + "?faces-redirect=true";

	}

	public String doLogout() {
		SessionContext.getInstance().encerrarSessao();
		Messages.addGlobalInfo("Logout realizado com sucesso !");
		return "/login.xhtml?faces-redirect=true";
	}

}
