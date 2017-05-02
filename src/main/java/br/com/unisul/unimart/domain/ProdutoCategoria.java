package br.com.unisul.unimart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
public class ProdutoCategoria extends GenericDomain {

	@Column(length=45)
	private String nome;
	@Lob
	@Column()
	private byte[] imagem;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
}
