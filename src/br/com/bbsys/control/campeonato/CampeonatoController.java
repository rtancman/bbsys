package br.com.bbsys.control.campeonato;

import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.bbsys.dao.campeonato.Campeonato_dao;
import br.com.bbsys.model.campeonato.CampeonatoEtapa_model;
import br.com.bbsys.model.campeonato.Campeonato_model;

public class CampeonatoController {
	
	public boolean cadastrarCampeonato (Campeonato_model campeonatoModel){
		boolean resultado = false;
		try{
			Campeonato_dao campeonatoDao = new Campeonato_dao();
			resultado = campeonatoDao.cadastrarCampeonato(campeonatoModel);
			return resultado;
		}catch (Exception e) {
			e.getMessage();
			return false;
		}
		
	}
	
	public static ArrayList<Campeonato_model> relatorioCampeonato(){
		
		ArrayList<Campeonato_model> listaCampeonato = null;
		
		listaCampeonato = Campeonato_dao.relatorioCampeonato();
		
		return listaCampeonato;
		
	}
	
	public static ArrayList<Campeonato_model> listar(Campeonato_model campeonatoModel, String ordenar){
		
		ArrayList<Campeonato_model> listaCampeonato = null;
		
		listaCampeonato = Campeonato_dao.listar(campeonatoModel, ordenar);
		
		return listaCampeonato;
		
	}
	
	/*
	public static ArrayList<Atleta_model> relatorioCampeonato(){
		
		ArrayList<Atleta_model> listaAtletaCampeonato = null;
		
		listaAtletaCampeonato = Campeonato_dao.relatorioCampeonato();
		
		return listaCampeonato;
		
	}*/

	/**
     * Funcao que carrega um Campeonato_model apartir da instancia atual
     * @param	Campeonato_model campeonatoModel
     * @return	Campeonato_model campeonatoModel
     *  */
    public Campeonato_model carregar( Campeonato_model campeonatoModel ){
    	    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Campeonato_model campeonatoModelAux = null; 
    	Campeonato_dao campeonatoDao = new Campeonato_dao();
    	
    	campeonatoModelAux = campeonatoDao.carregar(campeonatoModel);
    	
    	return campeonatoModelAux;
    	
    }
    
    /**
     * Funcao que carrega um campeonato inteiro apartir da instancia atual de Campeonato_model
     * @param	Campeonato_model campeonatoModel
     * @return	Campeonato_model campeonatoModel
     *  */
    public Campeonato_model carregarCampeonatoInteiro ( Campeonato_model campeonatoModel ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Campeonato_model campeonatoModelAux = null; 
    	Campeonato_dao campeonatoDao = new Campeonato_dao();
    	
    	campeonatoModelAux = campeonatoDao.carregarCampeonatoInteiro(campeonatoModel);
    	
    	return campeonatoModelAux;
    	
    }
    
    /**
     * Funcao que seta a instancia de campeonatoModel apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	Campeonato_model campeonatoModel
     *  */
    public static Campeonato_model carregarResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Campeonato_model campeonatoModel = new Campeonato_model();
    	
    	try{
	    	if( res.next() ){
    			campeonatoModel = new Campeonato_model();
    			campeonatoModel.setIdcampeonato(res.getInt("idcampeonato"));
 	            campeonatoModel.setNome(res.getString("nome"));
 	            campeonatoModel.setDataInicio(res.getDate("dataInicio"));         
 	            campeonatoModel.setDataEncerramento(res.getDate("dataEncerramento"));         
 	            campeonatoModel.setDataCadastro(res.getDate("dataCadastro"));    
			}
	    	return campeonatoModel;
	    	
    	}catch (Exception e) {
    		return null;
		}	
    	
    }
    
    /**
     * Funcao que retorna um arrayList<Campeonato_model> apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	ArrayList<Campeonato_model>
     *  */
    public static ArrayList<Campeonato_model> carregarListaResultSet( ResultSet res ){
    	
    	//Inicio as variaveis que ser�o utilizados para este metodo
    	ArrayList<Campeonato_model> listaCampeonato = null;
    	Campeonato_model campeonatoModel = null;
    	
    	try{
    		listaCampeonato = new ArrayList<Campeonato_model>();
    		while(res.next()){
    			campeonatoModel = new Campeonato_model();
    			campeonatoModel.setIdcampeonato(res.getInt("idcampeonato"));
 	            campeonatoModel.setNome(res.getString("nome"));
 	            campeonatoModel.setDataInicio(res.getDate("dataInicio"));         
 	            campeonatoModel.setDataEncerramento(res.getDate("dataEncerramento"));         
 	            campeonatoModel.setDataCadastro(res.getDate("dataCadastro"));          
    			listaCampeonato.add(campeonatoModel); 
    		}
    		return listaCampeonato;
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }

    /**
     * Funcao que edita um Campeonato_model apartir de uma instancia
     * @param	Campeonato_model campeonatoModel
     * @return	boolean
     *  */
    public boolean editar(Campeonato_model campeonatoModel){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	boolean result = false;
    	Campeonato_dao campeonatoDao = new Campeonato_dao();
    	
    	result = campeonatoDao.editar(campeonatoModel);
    	
    	return result;
    	
    }
    
	/**
	 * Funcao que carrega um campeonato inteiro com todas as etapas e baterias 
	 * @param Campeonato_model
	 * return Campeonato_model
	 * */
    public Campeonato_model carregarCampeonatoInteiroResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Campeonato_model campeonatoModel = null;
    	CampeonatoEtapa_model campeonatoEtapaModel = null;
    	CampeonatoEtapaController campeonatoEtapaControl = null;
    	
    	try{
    		if( res.next() ){
    			campeonatoModel = new Campeonato_model();                  
    			//campeonato
    			campeonatoModel.setIdcampeonato(res.getInt("c.idcampeonato"));
    			campeonatoModel.setNome(res.getString("c.nome"));
    			campeonatoModel.setDataInicio(res.getDate("c.dataInicio"));         
    			campeonatoModel.setDataEncerramento(res.getDate("c.dataEncerramento"));         
    			campeonatoModel.setDataCadastro(res.getDate("c.dataCadastro"));
    			//etapa campeonato lista
    			campeonatoEtapaModel = new CampeonatoEtapa_model();
    			campeonatoEtapaModel.setIdcampeonato(res.getInt("c.idcampeonato"));
    			campeonatoEtapaControl = new CampeonatoEtapaController();
    			campeonatoEtapaControl.carregarListaCampeonatoEtapaInteiro(campeonatoEtapaModel);
    		}
    		return campeonatoModel;
    		
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }
    
}
