package br.com.unisul.unimart.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.ClienteDao;
import br.com.unisul.unimart.dao.EnderecoDao;
import br.com.unisul.unimart.domain.Cliente;
import br.com.unisul.unimart.domain.Endereco;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class EnderecoBean extends GenericBean {

	private String rua;
	private String numero;
	private String cidade;
	private String UF;
	private String bairro;
	private String cep;

	public Endereco end;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void novo() {
		end = new Endereco();
	}

	public void buscaCod() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession sess = (HttpSession) facesContext.getExternalContext().getSession(true);
		Cliente c = (Cliente) sess.getAttribute("user");
		if (c.getEndereco() != null) {
			try {
				novo();
				EnderecoDao endDao = new EnderecoDao();
				end = endDao.buscar(c.getEndereco().getCodigo().longValue());
			} catch (Exception e) {
				Messages.addGlobalError("Erro ao encontrar Endereço");
				e.printStackTrace();
			}

			setRua(end.getRua());
			setNumero(end.getNumero());
			setBairro(end.getBairro());
			setCep(end.getCep());
			setCidade(end.getCidade());
			setUF(end.getUF());
		} else {
			setRua("");
			setNumero("");
			setBairro("");
			setCep("");
			setCidade("");
			setUF("");
		}
	}

	public void excluir(ActionEvent event) {
		try {
			end = (Endereco) event.getComponent().getAttributes().get("enderecoExcluir");
			EnderecoDao endDao = new EnderecoDao();
			endDao.excluir(end);
			buscaCod();
			Messages.addGlobalInfo(end.getRua() + " exluído com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir endereco");
			e.printStackTrace();
		}

	}

	public void alterar(ActionEvent event) {
		end = (Endereco) event.getComponent().getAttributes().get("enderecoAlterar");
	}

	public void salvar() {
		try {
			EnderecoDao enderecoDao = new EnderecoDao();
			enderecoDao.merge(end);
			Messages.addGlobalInfo("Cliente cadastrado com sucesso");
			novo();
			buscaCod();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar Endereco");
			e.printStackTrace();
		}
	}
}
