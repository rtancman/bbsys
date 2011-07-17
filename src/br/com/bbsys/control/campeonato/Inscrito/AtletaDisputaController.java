package br.com.bbsys.control.campeonato.Inscrito;

import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.bbsys.dao.campeonato.Inscrito.AtletaDisputa_dao;
import br.com.bbsys.model.campeonato.Bateria_model;
import br.com.bbsys.model.campeonato.Inscrito.AtletaDisputa_model;
import br.com.bbsys.model.usuario.Atleta_model;

public class AtletaDisputaController {

	/**
     * Funcao para persistir um atletaDisputa_model
     * @param	AtletaDisputa_model atletaDisputaModel
     * @return	boolean
     *  */
	public boolean salvarAtletaDisputa( AtletaDisputa_model atletaDisputaModel ){
		
		//Inicio as variaveis que ser�o utilizados para este metodo
    	boolean resultado = false;
    	//AtletaDisputa_model atletaDisputaModelAux = null;
    	
    	try{
    		
    		AtletaDisputa_dao atletaDisputaDao = new AtletaDisputa_dao();
    		
			//salvo o atleta que vai disputar a bateria bateria 
			//bateriaModelAux = atletaDisputaDao.salvarAtletaDisputa(atletaDisputaModel);
    		resultado = atletaDisputaDao.salvarAtletaDisputa(atletaDisputaModel);
    			
    	}catch (Exception e) {
    		return false;
    	}	
    	
    	return resultado;
	}
	
	/**
	 * Funcao para deletar um atletaDisputa_model
	 * @param	AtletaDisputa_model atletaDisputaModel
	 * @return	boolean
	 *  */
	public Boolean deletarAtletaDisputa( AtletaDisputa_model atletaDisputaModel ){
		
		//Inicio as variaveis que ser�o utilizados para este metodo
		boolean resultado = false;
		
		try{
			
			AtletaDisputa_dao atletaDisputaDao = new AtletaDisputa_dao();
			
			//salvo o atleta que vai disputar a bateria bateria 
			resultado = atletaDisputaDao.deletarAtletaDisputa(atletaDisputaModel);
			
		}catch (Exception e) {
			return false;
		}	
		
		return resultado;
	}
	
	/**
	 * Funcao para deletar todos os atletas que estao diputando uma bateria
	 * @param	AtletaDisputa_model atletaDisputaModel
	 * @return	boolean
	 *  */
	public Boolean deletarTodosAtletaDisputa( AtletaDisputa_model atletaDisputaModel ){
		
		//Inicio as variaveis que ser�o utilizados para este metodo
		boolean resultado = false;
		
		try{
			
			AtletaDisputa_dao atletaDisputaDao = new AtletaDisputa_dao();
			
			//salvo o atleta que vai disputar a bateria bateria 
			resultado = atletaDisputaDao.deletarAtletaDisputa(atletaDisputaModel);
			
		}catch (Exception e) {
			return false;
		}	
		
		return resultado;
	}
	
	/**
    * Funcao que carrega uma lista de atletaDisputaModel apartir de um obj ResultSet
    * @param	ResultSet res
    * @return	Atleta_model atletaModel
    **/
   public ArrayList<AtletaDisputa_model> carregarListaAtletaDisputa( ResultSet res ){
   	
   	//Inicio os objetos que ser�o utilizados para este metodo
	   ArrayList<AtletaDisputa_model> listaAtletaDisputaModel = null;
	   AtletaDisputa_model atletaDisputaModel = null;
	   Atleta_model atletaModel = null;
	   Bateria_model bateriaModel = null;
	 
	   try{
   			listaAtletaDisputaModel = new ArrayList<AtletaDisputa_model>();
	    	while(res.next()){
	    		
	    		atletaDisputaModel = new AtletaDisputa_model();
	    		//obj atletadisputa
	    		atletaDisputaModel.setIdatletadisputa(res.getInt("ad.idatletadisputa"));
	    		atletaDisputaModel.setCamisetacor(res.getString("ad.camisetaCor"));
	    		atletaDisputaModel.setDataCadastro(res.getDate("ad.dataCadastro"));    
			    //obj Atleta
	    		atletaModel = new Atleta_model();
			    atletaModel.setidatleta(res.getInt("ad.idatleta"));
			    atletaModel.setNome(res.getString("u.nome"));
			    atletaModel.setidatleta(res.getInt("ad.idatleta"));
			    atletaDisputaModel.setAtletaModel(atletaModel);
			    //obj bateria
			    bateriaModel = new Bateria_model();
			    bateriaModel.setIdbateria(res.getInt("ad.idbateria"));
			    atletaDisputaModel.setBateriaModel(bateriaModel);
			    
			    listaAtletaDisputaModel.add(atletaDisputaModel);
			}
	    	
	    	return listaAtletaDisputaModel;
	    	
	   	}catch (Exception e) {
	   		return null;
		}	
   	
   }
   
   /**
    * Funcao para listar os atletas que estao disputando uma bateria retorna um ArrayList<AtletaDisputa_model>
    * @param	ResultSet res
    * @return	Atleta_model atletaModel
    **/
   public ArrayList<AtletaDisputa_model> listarAtletaDisputa( AtletaDisputa_model atletaDisputaModel ){
	   
	   //Inicio os objetos que ser�o utilizados para este metodo
	   ArrayList<AtletaDisputa_model> listaAtletaDisputaModel = null;
	   String tipoOrdem = "ad.idatletadisputa";
	   
	   try{
		   
		   AtletaDisputa_dao atletaDisputaDao = new AtletaDisputa_dao();
			 
		   listaAtletaDisputaModel = atletaDisputaDao.listarAtletaDisputa(atletaDisputaModel, tipoOrdem);
			
		   return listaAtletaDisputaModel;
		   
	   }catch (Exception e) {
		   return null;
	   }	
	   
   }
   

   /**
    * Funcao que edita a nota do atleta na bateria
    * @param	AtletaDisputa_model atletaDisputaModel
    * @return	boolean
    *  */
   public static boolean editarNotaAtletaDisputa(AtletaDisputa_model atletaDisputaModel){
   	
   	try {
   		
   		boolean resultado = AtletaDisputa_dao.editarNotaAtletaDisputa(atletaDisputaModel);
       	
       	return resultado;
       	
		} catch (Exception e) {
			
			return false;
			
		}
   	
   	
   }
	
}
