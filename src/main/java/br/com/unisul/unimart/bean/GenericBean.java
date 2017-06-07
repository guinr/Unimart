package br.com.unisul.unimart.bean;

import java.io.Serializable;
import java.util.Base64;

import org.primefaces.model.UploadedFile;

public abstract class GenericBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected UploadedFile file;
	
	// Gets e Sets
    
    public String getImagemBase64(byte[] imagem) {
    	if (imagem == null) return null;
		return "data:image/jpg;base64," + Base64.getMimeEncoder().encodeToString(imagem);
	}
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    // Metodos
    
    public void novo() {
    	file = null;
    }
	
}