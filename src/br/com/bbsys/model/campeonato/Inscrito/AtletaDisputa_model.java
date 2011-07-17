package Model.Campeonato.Inscrito;

import java.sql.Date;
import java.util.ArrayList;

import Model.Campeonato.Bateria_model;
import Model.Campeonato.Julgamento.Julgamento_model;
import Model.Usuario.Atleta_model;

public class AtletaDisputa_model {

	private int idatletadisputa;
	private int idinscricaoatleta;
	private String camisetacor;
	private Atleta_model atletaModel;
	private Bateria_model bateriaModel;
	private ArrayList<Julgamento_model> listaJulgamentoModel;
	private double notaFinal;
	private Date dataCadastro;
	
	/**
	 * Construtor da classe - Função que inicializa o obj com os atributos "camisetacor", "atletaModel" e "bateriaModel"
	 * @param Atleta_model atletaModel
	 * @param Bateria_model bateriaModel
	 * @param String camisetaCor 
	 * @return AtletaDisputa_model
	 * */
	public AtletaDisputa_model(Atleta_model atletaModel, Bateria_model bateriaModel, String camisetaCor ){
		this.atletaModel = atletaModel;
		this.bateriaModel= bateriaModel;
		this.camisetacor = camisetaCor;
	}
	public AtletaDisputa_model(){
		
	}
	
	public int getIdatletadisputa() {
		return idatletadisputa;
	}
	public void setIdatletadisputa(int idatletadisputa) {
		this.idatletadisputa = idatletadisputa;
	}
	public String getCamisetacor() {
		return camisetacor;
	}
	public void setCamisetacor(String camisetacor) {
		this.camisetacor = camisetacor;
	}
	public Atleta_model getAtletaModel() {
		return atletaModel;
	}
	public void setAtletaModel(Atleta_model atletaModel) {
		this.atletaModel = atletaModel;
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
	
	public int getIdinscricaoatleta() {
		return idinscricaoatleta;
	}
	
	public void setIdinscricaoatleta(int idinscricaoatleta) {
		this.idinscricaoatleta = idinscricaoatleta;
	}
	
	public ArrayList<Julgamento_model> getListaJulgamentoModel() {
		return listaJulgamentoModel;
	}
	
	public void setListaJulgamentoModel(
			ArrayList<Julgamento_model> listaJulgamentoModel) {
		this.listaJulgamentoModel = listaJulgamentoModel;
	}
	public double getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	
	
	
}
