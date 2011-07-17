package Model.Campeonato;

import java.util.ArrayList;
import java.util.Date;

public class Campeonato_model {
	
	/**
	 * Atributos da classe campeonato
	 * */
	private int idcampeonato;
	private String nome;
	private Date dataInicio;
	private Date dataEncerramento;
	private Date dataCadastro;
	private int status;
	private ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa;
	
	/**
	 * Metodos encapsuladores da classe campeonato
	 * */
	public int getIdcampeonato() {
		return idcampeonato;
	}
	public void setIdcampeonato(int idcampeonato) {
		this.idcampeonato = idcampeonato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<CampeonatoEtapa_model> getListaCampeonatoEtapa() {
		return listaCampeonatoEtapa;
	}
	public void setListaCampeonatoEtapa(
			ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa) {
		this.listaCampeonatoEtapa = listaCampeonatoEtapa;
	}
	
	
}
