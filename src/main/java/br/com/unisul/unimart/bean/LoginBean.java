package br.com.unisul.unimart.bean;

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
public class LoginBean extends GenericBean {

	private Cliente clienteLogado;
	private Cliente cliente;
	private String email;
	private String senha;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void novo() {
		cliente = new Cliente();
	}

	public void cancelar() {
		novo();
	}

	public String doLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpSession sess = (HttpSession) facesContext.getExternalContext().getSession(true);

		ClienteDao c = new ClienteDao();
		clienteLogado = c.listarPorEmailSenha(getEmail(), getSenha());

		if (clienteLogado == null) {
			FacesContext.getCurrentInstance().validationFailed();
			Messages.addGlobalError("Erro ao efetuar login"); 
			return "";
		}

		sess.setAttribute("logado", true);
		sess.setAttribute("user", clienteLogado);
		Cliente user = (Cliente) sess.getAttribute("user");
		sess.setAttribute("adm", (Boolean) clienteLogado.getAdmin());
		System.out.println(user.getNome() + " Logado");
		Messages.addGlobalInfo("Login efetuado com sucesso");
		return sess.getAttribute("lastPage").toString() + "?faces-redirect=true";

	}

	public String doLogout() {
		SessionContext.getInstance().encerrarSessao();
		Messages.addGlobalInfo("Logout realizado com sucesso!");
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String authFailed(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession sess = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		return sess.getAttribute("previousPage").toString() + "?faces-redirect=true";
	}

}
