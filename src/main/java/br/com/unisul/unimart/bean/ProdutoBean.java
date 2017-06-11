package br.com.unisul.unimart.bean;

import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.unisul.unimart.dao.ProdutoCategoriaDao;
import br.com.unisul.unimart.dao.ProdutoDao;
import br.com.unisul.unimart.domain.Produto;
import br.com.unisul.unimart.domain.ProdutoCategoria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean extends GenericBean {
	
	private Produto produto;
	private	List<Produto> produtoList;
	private List<ProdutoCategoria> produtoCategoriaList;
	private Integer codigoCategoriaAtual;
	
	//Gets, Sets
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}
	
	public List<ProdutoCategoria> getProdutoCategoriaList() {
		return produtoCategoriaList;
	}

	public void setProdutoCategoriaList(List<ProdutoCategoria> ProdutoCategoriaList) {
		this.produtoCategoriaList = ProdutoCategoriaList;
	}
	
	public String getImagemBase64(byte[] imagem) {
		return "data:image/jpg;base64," + Base64.getMimeEncoder().encodeToString(imagem);
	}
	
	// Produto
	@PostConstruct
	public void listar() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoList = produtoDao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar produtos");
			e.printStackTrace();
		}
	}
	
	public void listarByCategoria(Integer codigo) {
		if (codigo == null) {
			listar();
		} else {
			if (codigo != codigoCategoriaAtual) {
				try {
					ProdutoDao produtoDao = new ProdutoDao();
					ProdutoCategoria cat = new ProdutoCategoria();
					cat.setCodigo(codigo);
					produtoList = produtoDao.listarPorCategoria(cat);
					codigoCategoriaAtual = codigo;
				} catch (Exception e) {
					Messages.addGlobalError("Erro ao listar produtos");
					e.printStackTrace();
				}
			}
		}
	}
	
	public void novo() {
		produto = new Produto();
		carregaProdutoCategoriaList();
	}
	
	public void excluir(ActionEvent event) {
		try {
			produto = (Produto)event.getComponent().getAttributes().get("produtoExcluir");
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.excluir(produto);
			listar();
			Messages.addGlobalInfo(produto.getNome() + " exluído com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir produto");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent event) {
		produto = (Produto)event.getComponent().getAttributes().get("produtoAlterar");
		carregaProdutoCategoriaList();
	}

	public void salvar() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.merge(produto);
			Messages.addGlobalInfo("Produto cadastrado com sucesso");
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar produto");
			e.printStackTrace();
		}
	}
	
	// Produto Categoria
	private void carregaProdutoCategoriaList() {
		try {
			ProdutoCategoriaDao produtoCategoriaDao = new ProdutoCategoriaDao();
			produtoCategoriaList = produtoCategoriaDao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar categoria de produto");
			e.printStackTrace();
		}
	}
	
}