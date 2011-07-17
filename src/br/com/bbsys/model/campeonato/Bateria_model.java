package Model.Campeonato;

import java.util.ArrayList;
import java.util.Date;

import Model.Campeonato.Inscrito.AtletaDisputa_model;

public class Bateria_model extends CampeonatoEtapa_model {
	
	private int idbateria;
	private int fase;
	private int tempo;
	private int intervaloTempo;
	private int status;
	private Date tempoInicio;
	private Date tempoEncerramento;
	private Date dataCadastroBateria;
	private ArrayList<AtletaDisputa_model> listaAtletaDisputa;
	

	//Construtor da classe
	
	public Bateria_model(){
		super();
	}
	
	/**
	 * Construtor da classe - Função que inicializa o obj com os atributos int fase, int tempo, int intervaloTempo, Date tempoInicio, Date tempoEncerramento, ArrayList<AtletaDisputa_model> listaAtletaDisputa
	 * @param int fase
	 * @param int tempo
	 * @param int intervaloTempo
	 * @param Date tempoInicio
	 * @param Date tempoEncerramento
	 * @param ArrayList<AtletaDisputa_model> listaAtletaDisputa
	 * @return Bateria_model
	 * */
	public Bateria_model(int fase, int tempo, int intervaloTempo, Date tempoInicio, Date tempoEncerramento, ArrayList<AtletaDisputa_model> listaAtletaDisputa){
		this.fase = fase;
		this.tempo = tempo;
		this.intervaloTempo = intervaloTempo;
		this.tempoInicio = tempoInicio;
		this.tempoEncerramento = tempoEncerramento;
		this.listaAtletaDisputa = listaAtletaDisputa;
	}
	
	//metodos de encapsulamento
	public int getIdbateria() {
		return idbateria;
	}
	public void setIdbateria(int idbateria) {
		this.idbateria = idbateria;
	}
	public int getFase() {
		return fase;
	}
	public void setFase(int fase) {
		this.fase = fase;
	}
	
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
	public int getIntervaloTempo() {
		return intervaloTempo;
	}

	public void setIntervaloTempo(int intervaloTempo) {
		this.intervaloTempo = intervaloTempo;
	}

	public Date getTempoInicio() {
		return tempoInicio;
	}
	public void setTempoInicio(Date tempoInicio) {
		this.tempoInicio = tempoInicio;
	}
	public Date getTempoEncerramento() {
		return tempoEncerramento;
	}
	public void setTempoEncerramento(Date tempoEncerramento) {
		this.tempoEncerramento = tempoEncerramento;
	}
	public Date getDataCadastroBateria() {
		return dataCadastroBateria;
	}
	public void setDataCadastroBateria(Date dataCadastroBateria) {
		this.dataCadastroBateria = dataCadastroBateria;
	}
	
	public ArrayList<AtletaDisputa_model> getListaAtletaDisputa() {
		return listaAtletaDisputa;
	}

	public void setListaAtletaDisputa(
			ArrayList<AtletaDisputa_model> listaAtletaDisputa) {
		this.listaAtletaDisputa = listaAtletaDisputa;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
