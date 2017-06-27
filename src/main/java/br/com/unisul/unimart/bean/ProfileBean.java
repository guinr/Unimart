package br.com.unisul.unimart.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ProfileBean extends GenericBean {

	private Boolean editMode = false;

	public void editar() {
		editMode = true;
	}

	public void salvar() {
		editMode = false;
	}

	public Boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(Boolean editMode) {
		this.editMode = editMode;
	}

}
