package br.com.unisul.unimart.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class clienteBean implements Serializable {

	public void salvar() {
		FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar", null);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, fMsg);
	}
	
}