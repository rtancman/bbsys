package Model.Campeonato.Inscrito;

import java.sql.Date;

import Model.Campeonato.Bateria_model;
import Model.Usuario.Juiz_model;

public class JuizBateria_model {

	private int idjuizbateria;
	private int idinscricaojuiz;
	private Juiz_model juizModel;
	private Bateria_model bateriaModel;
	private Date dataCadastro;
	
	public int getIdjuizbateria() {
		return idjuizbateria;
	}
	public void setIdjuizbateria(int idjuizbateria) {
		this.idjuizbateria = idjuizbateria;
	}
	public int getIdinscricaojuiz() {
		return idinscricaojuiz;
	}
	public void setIdinscricaojuiz(int idinscricaojuiz) {
		this.idinscricaojuiz = idinscricaojuiz;
	}
	public Juiz_model getJuizModel() {
		return juizModel;
	}
	public void setJuizModel(Juiz_model juizModel) {
		this.juizModel = juizModel;
	}
	public Bateria_model getBateriaModel() {
		return bateriaModel;
	}
	public void setBateriaModel(Bateria_model bateriaModel) {
		this.bateriaModel = bateriaModel;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
}
