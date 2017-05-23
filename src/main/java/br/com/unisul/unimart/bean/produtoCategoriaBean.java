package br.com.unisul.unimart.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.model.UploadedFile;

import br.com.unisul.unimart.dao.ProdutoCategoriaDao;
import br.com.unisul.unimart.domain.ProdutoCategoria;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class produtoCategoriaBean implements Serializable {
	
	private ProdutoCategoria produtoCategoria;
	private	List<ProdutoCategoria> produtoCategoriaList;
	private UploadedFile file;
	
	@SuppressWarnings("unused")
	private String imagemBase64;
	
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
	
	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public String getImagemBase64(byte[] imagem) {
    	if(imagem!=null){
    		return "data:image/jpg;base64," + Base64.getMimeEncoder().encodeToString(imagem);
    	}
    	else{
    		File f = new File("/src/img/SemImagem.png");
    		try {
				imagem = Files.readAllBytes(f.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
    		return "data:image/jpg;base64," + Base64.getMimeEncoder().encodeToString(imagem);
    	}
	}
    
    public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
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
			if (!file.getFileName().equals("")) {
			    //--colocar imagem no produtoCategoria
			    produtoCategoria.setImagem(file.getContents());
			}else{
				//--se não tiver imagem selecionada cadastra uma imagem padrao
				//--Alterar para pegar caminho relativo
				//--Atualizaaa
				File f = new File("C:\\Users\\heto1\\git\\Unimart\\src\\img\\SemImagem.png");
	    		try {
					produtoCategoria.setImagem(Files.readAllBytes(f.toPath()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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