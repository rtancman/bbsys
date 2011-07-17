package Model.Campeonato.Julgamento;

import java.util.ArrayList;
import java.util.Date;

import Model.Campeonato.Inscrito.AtletaDisputa_model;

public class Onda_model {

	private int idonda;
	private AtletaDisputa_model atletaDisputaModel;
	private ArrayList<Julgamento_model> listaJulgamentoModel;
	private double notaParcial;
	private int ordem;
	private Date dataCadastro;
	
	public int getIdonda() {
		return idonda;
	}
	public void setIdonda(int idonda) {
		this.idonda = idonda;
	}
	public AtletaDisputa_model getAtletaDisputaModel() {
		return atletaDisputaModel;
	}
	public void setAtletaDisputaModel(AtletaDisputa_model atletaDisputaModel) {
		this.atletaDisputaModel = atletaDisputaModel;
	}
	public ArrayList<Julgamento_model> getListaJulgamentoModel() {
		return listaJulgamentoModel;
	}
	public void setListaJulgamentoModel(
			ArrayList<Julgamento_model> listaJulgamentoModel) {
		this.listaJulgamentoModel = listaJulgamentoModel;
	}
	public double getNotaParcial() {
		return notaParcial;
	}
	public void setNotaParcial(double notaParcial) {
		this.notaParcial = notaParcial;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
		
}
