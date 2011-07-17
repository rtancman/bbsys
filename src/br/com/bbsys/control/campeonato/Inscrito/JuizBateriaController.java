package br.com.bbsys.control.campeonato.Inscrito;

import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.bbsys.dao.campeonato.Inscrito.JuizBateria_dao;
import br.com.bbsys.model.campeonato.Inscrito.JuizBateria_model;
import br.com.bbsys.model.usuario.Juiz_model;

public class JuizBateriaController {
	
	/**
	 * Funcao para carregar um obj JuizBateria_model
	 * @param JuizBateria_model juizBateriaModel
	 * @return JuizBateria_model juizBateriaModel
	 * */
	public JuizBateria_model carregar(JuizBateria_model juizBateriaModel){
		
		JuizBateria_model juizBateriaModelAux = null;
		
		try {
			
			JuizBateria_dao juizBateriaDao = new JuizBateria_dao();
			juizBateriaModelAux = juizBateriaDao.carregar(juizBateriaModel);
			return juizBateriaModelAux;
		
		} catch (Exception e) {
		
			return null;
		
		}
		
	}
	
	/**
	 * Funcao para carregar um obj JuizBateria_model
	 * @param ArrayList<Juiz_model> listaJuizInscritoModel
	 * @return ArrayList<JuizBateria_model> listaJuizInscritoBateriaModel
	 * */
	public ArrayList<JuizBateria_model> carregarListaJuizInscrito( ArrayList<Juiz_model> listaJuizInscritoModel){
		
		ArrayList<JuizBateria_model> listaJuizInscritoBateriaModel = null;
		JuizBateria_dao juizBateriaDao = null;
		JuizBateria_model juizBateriaModelAux = null;
		
		try {
			
			listaJuizInscritoBateriaModel = new ArrayList<JuizBateria_model>();
			
			for(Juiz_model juizModelAux : listaJuizInscritoModel){
				
				juizBateriaModelAux = new JuizBateria_model();
				juizBateriaModelAux.setJuizModel(juizModelAux);

				//carrego o juizInscritoBAteria
				juizBateriaDao = new JuizBateria_dao();
				juizBateriaModelAux = juizBateriaDao.carregarJuizInscrito(juizBateriaModelAux);
				
				//gravo na lista
				listaJuizInscritoBateriaModel.add(juizBateriaModelAux);
				
			}
			
			return listaJuizInscritoBateriaModel;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	/**
	    * Funcao que carrega uma lista de atletaDisputaModel apartir de um obj ResultSet
	    * @param	ResultSet res
	    * @return	JuizBateria_model juizBateriaModel
	    **/
	   public JuizBateria_model carregarJuizBateria( ResultSet res ){
	   	
	   		//Inicio os objetos que ser�o utilizados para este metodo
		   JuizBateria_model juizBateriaModel = null;
		   Juiz_model juizModel = null;
		 
		   try{
			   
		    	while(res.next()){
		    		
		    		//obj JuizBateria_model();
		    		juizBateriaModel = new JuizBateria_model();
		    		juizBateriaModel.setIdinscricaojuiz(res.getInt("jb.idinscricaojuiz"));
		    		juizBateriaModel.setIdjuizbateria(res.getInt("jb.idjuizbateria"));
		    		   
				    //obj Juiz_model()
		    		juizModel = new Juiz_model();
		    		juizModel.setId(res.getInt("u.idusuario"));
		    		juizModel.setNome(res.getString("u.nome"));
		    		juizModel.setIdjuiz(res.getInt("j.idjuiz"));
		    		juizBateriaModel.setJuizModel(juizModel);
				    
				}
		    	
		    	return juizBateriaModel;
		    	
		   	}catch (Exception e) {
		   		return null;
			}	
	   	
	   }
	   
	   /**
	    * Funcao que carrega uma lista de juizBateriaModel apartir de um obj ResultSet
	    * @param	ResultSet res
	    * @return	JuizBateria_model juizBateriaModel
	    **/
	   public JuizBateria_model carregarJuizInscritoResultSet( ResultSet res ){
		   
		   //Inicio os objetos que ser�o utilizados para este metodo
		   JuizBateria_model juizBateriaModel = null;
		   Juiz_model juizModel = null;
		   
		   try{
			   
			   while(res.next()){
				   
				   //obj JuizBateria_model();
				   juizBateriaModel = new JuizBateria_model();
				   juizBateriaModel.setIdinscricaojuiz(res.getInt("ij.idinscricaojuiz"));
				   
				   //obj Juiz_model()
				   juizModel = new Juiz_model();
				   juizModel.setId(res.getInt("u.idusuario"));
				   juizModel.setNome(res.getString("u.nome"));
				   juizModel.setIdjuiz(res.getInt("j.idjuiz"));
				   juizBateriaModel.setJuizModel(juizModel);
				   
			   }
			   
			   return juizBateriaModel;
			   
		   }catch (Exception e) {
			   return null;
		   }	
		   
	   }
	   
		/**
	     * Funcao para persistir um JuizBateria
	     * @param	JuizBateria_model juizBateriaModel
	     * @return	boolean
	     *  */
		public boolean salvarJuizBateria( JuizBateria_model juizBateriaModel ){
			
			//Inicio as variaveis que ser�o utilizados para este metodo
	    	boolean resultado = false;
	    	//AtletaDisputa_model atletaDisputaModelAux = null;
	    	
	    	try{
	    		
	    		JuizBateria_dao juizBateriaDao = new JuizBateria_dao();
	    		
				//salvo o atleta que vai disputar a bateria bateria
	    		resultado = juizBateriaDao.salvarJuizBateria(juizBateriaModel);
	    			
	    	}catch (Exception e) {
	    		return false;
	    	}	
	    	
	    	return resultado;
		}

}
