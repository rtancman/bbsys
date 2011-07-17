package Model.Campeonato;

import java.util.ArrayList;
import java.util.Date;

public class CampeonatoEtapa_model extends Campeonato_model {

	private int idcampeonatoetapa;
	private String nomeEtapa;
	private String praia;
	private String cidade;
	private int idpais;
	private int idestado;
	private int status;
	private Date dataInicioEtapa;
	private Date dataEncerramentoEtapa;
	private Date dataCadastroEtapa;
	//lista de baterias
	private ArrayList<Bateria_model> listaBateria;
	
	public int getIdcampeonatoetapa() {
		return idcampeonatoetapa;
	}
	public void setIdcampeonatoetapa(int idcampeonatoetapa) {
		this.idcampeonatoetapa = idcampeonatoetapa;
	}
	public String getNomeEtapa() {
		return nomeEtapa;
	}
	public void setNomeEtapa(String nomeEtapa) {
		this.nomeEtapa = nomeEtapa;
	}
	public String getPraia() {
		return praia;
	}
	public void setPraia(String praia) {
		this.praia = praia;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Date getDataInicioEtapa() {
		return dataInicioEtapa;
	}
	public void setDataInicioEtapa(Date dataInicioEtapa) {
		this.dataInicioEtapa = dataInicioEtapa;
	}
	public Date getDataEncerramentoEtapa() {
		return dataEncerramentoEtapa;
	}
	public void setDataEncerramentoEtapa(Date dataEncerramentoEtapa) {
		this.dataEncerramentoEtapa = dataEncerramentoEtapa;
	}
	public Date getDataCadastroEtapa() {
		return dataCadastroEtapa;
	}
	public void setDataCadastroEtapa(Date dataCadastroEtapa) {
		this.dataCadastroEtapa = dataCadastroEtapa;
	}
	
	public int getIdpais() {
		return idpais;
	}
	public void setIdpais(int idpais) {
		this.idpais = idpais;
	}
	public int getIdestado() {
		return idestado;
	}
	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}
	public ArrayList<Bateria_model> getListaBateria() {
		return listaBateria;
	}
	public void setListaBateria(ArrayList<Bateria_model> listaBateria) {
		this.listaBateria = listaBateria;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
