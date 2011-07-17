package br.com.bbsys.model.usuario;

import java.util.Date;

public class Juiz_model extends Usuario_model {

	private int idjuiz;
	private Date dataCadastroJuiz;
	
	public Juiz_model(){
		super();
	}
	
	public Juiz_model(int idjuiz, Date dataCadastroJuiz){
		this.setIdjuiz(idjuiz);
		this.setDataCadastroJuiz(dataCadastroJuiz);
	}

	public int getIdjuiz() {
		return idjuiz;
	}

	public void setIdjuiz(int idjuiz) {
		this.idjuiz = idjuiz;
	}

	public Date getDataCadastroJuiz() {
		return dataCadastroJuiz;
	}

	public void setDataCadastroJuiz(Date dataCadastroJuiz) {
		this.dataCadastroJuiz = dataCadastroJuiz;
	}
	
	
}
