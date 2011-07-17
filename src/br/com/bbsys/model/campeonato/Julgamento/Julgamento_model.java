package Model.Campeonato.Julgamento;

import java.util.Date;

import Model.Campeonato.Inscrito.JuizBateria_model;

public class Julgamento_model {

	private int idjulgamento;
	
	private Onda_model ondaModel;
	
	private JuizBateria_model juizBateriaModel;
	
	private double nota;
	
	private Date dataCadastro;

	public int getIdjulgamento() {
		return idjulgamento;
	}

	public void setIdjulgamento(int idjulgamento) {
		this.idjulgamento = idjulgamento;
	}

	public Onda_model getOndaModel() {
		return ondaModel;
	}

	public void setOndaModel(Onda_model ondaModel) {
		this.ondaModel = ondaModel;
	}

	public JuizBateria_model getJuizBateriaModel() {
		return juizBateriaModel;
	}

	public void setJuizBateriaModel(JuizBateria_model juizBateriaModel) {
		this.juizBateriaModel = juizBateriaModel;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
