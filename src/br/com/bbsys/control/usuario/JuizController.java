package br.com.bbsys.control.usuario;

import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.bbsys.dao.usuario.Juiz_dao;
import br.com.bbsys.model.campeonato.CampeonatoEtapa_model;
import br.com.bbsys.model.usuario.Juiz_model;

public class JuizController extends UsuarioController {
	
	/**
	 * @category Fun��o para buscar um juiz que esta inscrito em algum campeonato no sistema
	 * @author Raffael
	 * @param int id - id do juiz
	 * @return ArrayList<CampeonatoEtapa_model>
	 * */
	public ArrayList<CampeonatoEtapa_model> juizInscritoEmCampeonato( int id ){
		
		Juiz_dao juizDao = new Juiz_dao();
		
		ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapaModel = juizDao.juizInscritoEmCampeonato(id);
		
		return listaCampeonatoEtapaModel;
	}
	
	/**
	 * @category Fun��o para buscar a lista de campeonatos que um Juiz nao esta inscrito
	 * @author Raffael
	 * @param int id - id do Juiz
	 * @return ArrayList<CampeonatoEtapa_model>
	 * */
	public ArrayList<CampeonatoEtapa_model> juizNaoInscritoEmCampeonato( int id ){
		
		Juiz_dao juizDao = new Juiz_dao();
		
		ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapaModel = juizDao.juizNaoInscritoEmCampeonato(id);
		
		return listaCampeonatoEtapaModel;
	}
	
	/**
	 * @category Fun��o para buscar um Juiz cadastrado no sistema
	 * @author Raffael
	 * @param String nomeBusca - nome a ser buscado
	 * @param int id - id a serbuscado
	 * @return Juiz_model
	 * */
	public Juiz_model buscar( String nome, int id ){
		
		Juiz_dao juizDao = new Juiz_dao();
		
		Juiz_model JuizModel = juizDao.buscar(nome,id);
		
		return JuizModel;
	}
	
	/**
	 * @category Fun��o para inscrever um Juiz em uma etapa de campeonato no sistema
	 * @author Raffael
	 * @param int idJuiz
	 * @param int idCampeonato
	 * @return Juiz_model
	 * */
	public boolean inscricao( int idJuiz, int idCampeonato ){
		
		Juiz_dao juizDao = new Juiz_dao();
		
		boolean resultado = juizDao.inscricao(idJuiz, idCampeonato);
		
		return resultado;
	}
	
	/**
	 * @category Fun��o para remover um Juiz da etapa de campeonato no sistema
	 * @author Raffael
	 * @param int idJuiz
	 * @param int idCampeonato
	 * @return Juiz_model
	 * */
	public boolean removerInscricao( int idJuiz, int idCampeonato ){
		
		Juiz_dao juizDao = new Juiz_dao();
		
		boolean resultado = juizDao.removerInscricao(idJuiz, idCampeonato);
		
		return resultado;
	}
	
	/**
	 * 
	 * @category Fun��o para listar os Juizes cadastrados no sistema
	 * @author Raffael
	 * @param String nomeBusca - lista por nome buscado
	 * @param String campoOrdem - ordena pelo campo passado
	 * @return ArrayList<Juiz_model>
	 * 
	 * */
    public static ArrayList<Juiz_model> listar(String nomeBusca, String campoOrdem){

    	ArrayList<Juiz_model> listaJuiz = null;
    	
    	listaJuiz = Juiz_dao.listar(nomeBusca,campoOrdem);
    	
    	if(listaJuiz != null && listaJuiz.size() > 0){
    		return listaJuiz;
    	}
    	
		return null;
		
	}
    
    /**
	 * @category Fun��o para cadastrar um juiz
	 * @author Raffael
	 * @param int usuarioID - id do usuario
	 * @return boolean
	 * */
	public boolean cadastrar( int usuarioID ){
		
		boolean resultado = false;
		
		Juiz_dao juizDao = new Juiz_dao();
		
		resultado = juizDao.cadastrar(usuarioID);
		
		return resultado;
	}


	/**
     * Funcao que carrega um Juiz_model completo apartir da instancia atual
     * @param	Juiz_model juizModel
     * @return	Juiz_model juizModel
     *  */
    public Juiz_model carregarUsuarioJuiz( Juiz_model juizModel ){
    	    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Juiz_model juizModelAux = new Juiz_model(); 
    	Juiz_dao juizDao = new Juiz_dao();
    	
    	juizModel = juizDao.carregarUsuarioJuiz(juizModel);
    	
    	return juizModel;
    	
    }
    
    /**
     * Funcao que seta a instancia de Juiz_model apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	Juiz_model juizModel
     *  */
    public static Juiz_model carregarUsuarioJuizResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Juiz_model juizModel = new Juiz_model(); 
    	try{
	    	while(res.next()){
	    		//obj usuario
	    		juizModel.setId(res.getInt("u.idusuario"));
	    		juizModel.setNome(res.getString("u.nome"));
			    juizModel.setEmail(res.getString("u.email"));         
			    juizModel.setSenha(res.getString("u.senha"));         
			    juizModel.setSexo(res.getString("u.sexo"));         
			    juizModel.setTelefone(res.getInt("u.telefone"));         
			    juizModel.setDataNascimento(res.getDate("u.dataNascimento"));         
			    juizModel.setDataCadastro(res.getDate("u.dataCadastro"));         
			    juizModel.setTipoUsuario(res.getInt("u.idtipousuario"));         
			    juizModel.setCidade(res.getString("u.idcidadeu"));         
			    //obj Juiz
			    juizModel.setIdjuiz(res.getInt("j.idjuiz"));
			    juizModel.setDataCadastroJuiz(res.getDate("j.dataCadastro"));
			}
	    	return juizModel;
    	}catch (Exception e) {
    		return null;
		}	
    	
    }
    
    /**
     * Funcao que seta a um ArraList<Juiz_model> apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	ArraList<Juiz_model>
     *  */
    public static ArrayList<Juiz_model> carregarListaUsuarioJuizResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Juiz_model juizModel = null;
    	ArrayList<Juiz_model> listajuizModel = null;

    	try{
    		listajuizModel = new ArrayList<Juiz_model>();
    		while(res.next()){
    			juizModel = new Juiz_model(); 
    			//obj usuario
    			juizModel.setId(res.getInt("u.idusuario"));
    			juizModel.setNome(res.getString("u.nome"));
    			juizModel.setEmail(res.getString("u.email"));         
    			juizModel.setSenha(res.getString("u.senha"));         
    			juizModel.setSexo(res.getString("u.sexo"));         
    			juizModel.setTelefone(res.getInt("u.telefone"));         
    			juizModel.setDataNascimento(res.getDate("u.dataNascimento"));         
    			juizModel.setDataCadastro(res.getDate("u.dataCadastro"));         
    			juizModel.setTipoUsuario(res.getInt("u.idtipousuario"));         
    			juizModel.setCidade(res.getString("u.idcidadeu"));         
    			//obj Juiz
			    juizModel.setIdjuiz(res.getInt("j.idjuiz"));
			    juizModel.setDataCadastroJuiz(res.getDate("j.dataCadastro"));
    			//adiciono o obj na lista
    			listajuizModel.add(juizModel);
    		}
    		return listajuizModel;
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }
    
    /**
     * Funcao que carrega um ArraList<Juiz_model> de Juizs inscritos em uma etapa de campeonato a partir do ResultSet da consulta feita na tabela incricaoJuiz
     * @param	ResultSet res
     * @return	ArraList<Juiz_model> listajuizModel
     *  */
    public ArrayList<Juiz_model> carregarListaUsuarioJuizInscritoResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Juiz_model juizModel = null;
    	ArrayList<Juiz_model> listaJuizModel = null;
    	
    	try{
    		listaJuizModel = new ArrayList<Juiz_model>();
    		while(res.next()){
    			
    			//obj Juiz
    			juizModel = new Juiz_model();         
    			juizModel.setIdjuiz(res.getInt("idjuiz"));
    			//carrego os dados deste obj
    			juizModel = this.carregarUsuarioJuiz(juizModel);
    			//adiciono o obj na lista
    			listaJuizModel.add(juizModel);
    			
    		}
    		return listaJuizModel;
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }
    
	/**
     * Funcao que valida a lista de juizes para montar a bateria
     * @param	ArrayList<Juiz_model> listaJuizInscritoModel
     * @return	String resposta
     *  */
    public String validarListaJuizParaMontarBateria( ArrayList<Juiz_model> listaJuizInscritoModel ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	String resposta = null;
    	    	
    	try{
    		
    		if(listaJuizInscritoModel.size() == 0 || listaJuizInscritoModel == null){
    			resposta = "Ops!<br/> N�o existem juizes para montar a bateria";
    			return resposta;
			}else if(listaJuizInscritoModel.size() < 3 ){
				resposta =  "Ops!<br/> Existem menos de 3 juizes cadastrados para esta etapa. Para montar uma bateria voc� precisa de no m�nimo 3 juizes cadastrados para esta etapa de campeontato.";
				return resposta;
			}else{
				resposta = "OK"; 
				return resposta; 
			}
    		
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }
    
    /**
	 * @category Fun��o para remover um juiz
	 * @author Raffael
	 * @param int idUsuario
	 * @return boolean
	 * */
	public static boolean removerJuiz( int idUsaurio ){
		
		boolean resultado = Juiz_dao.removerJuiz(idUsaurio);
		
		return resultado;
	}    
	
}
