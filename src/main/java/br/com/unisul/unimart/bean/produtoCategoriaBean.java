package br.com.unisul.unimart.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.ProdutoCategoriaDao;
import br.com.unisul.unimart.domain.ProdutoCategoria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class produtoCategoriaBean implements Serializable {
	
	private ProdutoCategoria produtoCategoria;
	private	List<ProdutoCategoria> produtoCategoriaList;
	
	public ProdutoCategoria getProdutoCategoria() {
		return produtoCategoria;
	}

	public void setProdutoCategoria(ProdutoCategoria ProdutoCategoria) {
		this.produtoCategoria = ProdutoCategoria;
	}

	public List<ProdutoCategoria> getProdutoCategoriaList() {
		return produtoCategoriaList;
	}

	public void setProdutoCategoriaList(List<ProdutoCategoria> ProdutoCategoriaList) {
		this.produtoCategoriaList = ProdutoCategoriaList;
	}
	
	@PostConstruct
	public void listar() {
		try {
			ProdutoCategoriaDao produtoCategoriaDao = new ProdutoCategoriaDao();
			produtoCategoriaList = produtoCategoriaDao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar categoria de produto");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		produtoCategoria = new ProdutoCategoria();
	}
	
	public void excluir(ActionEvent event) {
		try {
			produtoCategoria = (ProdutoCategoria)event.getComponent().getAttributes().get("produtoCategoriaExcluir");
			ProdutoCategoriaDao produtoCategoriaDao = new ProdutoCategoriaDao();
			produtoCategoriaDao.excluir(produtoCategoria);
			listar();
			Messages.addGlobalInfo(produtoCategoria.getNome() + " exluído com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir categoria de produto");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent event) {
		produtoCategoria = (ProdutoCategoria)event.getComponent().getAttributes().get("produtoCategoriaAlterar");
	}

	public void salvar() {
		try {
			ProdutoCategoriaDao produtoCategoriaDao = new ProdutoCategoriaDao();
			produtoCategoriaDao.merge(produtoCategoria);
			Messages.addGlobalInfo("Categoria de produto cadastrado com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar categoria de produto");
			e.printStackTrace();
		}
	}
	
}