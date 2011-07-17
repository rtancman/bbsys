package br.com.bbsys.model.usuario;

import java.util.Date;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

public class Gerente_model extends Usuario_model  {
	
	private int idgerente;
	private Date dataCadastroGerente;
	
	public Gerente_model(){
		super();
	}
	
	public int getIdgerente() {
		return idgerente;
	}

	public void setIdgerente(int idgerente) {
		this.idgerente = idgerente;
	}

	public Date getDataCadastroGerente() {
		return dataCadastroGerente;
	}

	public void setDataCadastroGerente(Date dataCadastroGerente) {
		this.dataCadastroGerente = dataCadastroGerente;
	}

}
