package br.com.bbsys.control.campeonato.Julgamento;

import java.sql.ResultSet;

import br.com.bbsys.dao.campeonato.Julgamento.Onda_dao;
import br.com.bbsys.model.campeonato.Inscrito.AtletaDisputa_model;
import br.com.bbsys.model.campeonato.Julgamento.Onda_model;

public class OndaController {

	/**
	 * Funcao para cadastro de ondas que um atleta surfou
	 * @param Onda_model ondaModel
	 * @return Onda_model ondaModel
	 * */
	public Onda_model cadastrarOnda (Onda_model ondaModel){
		
		Onda_model ondaModelAux = null;
		
		try{
		
			Onda_dao ondaDao = new Onda_dao();
			
			ondaModelAux = ondaDao.cadastrarOnda(ondaModel);
			
			return ondaModelAux;
			
		}catch (Exception e) {
			
			e.getMessage();
			return null;
			
		}
		
	}
	
	/**
     * Funcao que carrega a ultima onda surfada por um atleta
     * @param	Onda_model ondaModel
     * @return	Onda_model ondaModel
     *  */
    public Onda_model carregarUltimaOnda( Onda_model ondaModel,  String tipoOrdem ){
    	    	
    	Onda_model ondaModelAux = null;

    	try{
    		
			Onda_dao ondaDao = new Onda_dao();
			
			ondaModelAux = ondaDao.carregarUltimaOnda(ondaModel,tipoOrdem);
			
			return ondaModelAux;
			
		}catch (Exception e) {
			
			e.getMessage();
			return null;
			
		}
    	
    }
    
    /**
     * Funcao que seta a instancia de Onda_model apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	Onda_model ondaModel
     *  */
    public static Onda_model carregarOndaResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Onda_model ondaModel = new Onda_model(); 
    	AtletaDisputa_model atletaDisputaModel = null;
    	
    	try{
    		
	    	while(res.next()){
	    		
	    		//obj onda
	    		ondaModel.setIdonda(res.getInt("idondas"));
	    		ondaModel.setOrdem(res.getInt("ordem"));         
	    		ondaModel.setNotaParcial(res.getDouble("notafinal"));         
	    		ondaModel.setDataCadastro(res.getDate("dataCadastro"));
	    		//obj atleta disputa
	    		atletaDisputaModel = new AtletaDisputa_model();
	    		atletaDisputaModel.setIdatletadisputa(res.getInt("idatletadisputa"));
	    		ondaModel.setAtletaDisputaModel(atletaDisputaModel);
	    		
			}
	    	
	    	return ondaModel;
	    	
    	}catch (Exception e) {
    		return null;
		}	
    	
    }
    
    /**
     * Funcao que edita uma notaFinal de uma onda apartir de uma instancia
     * @param	Onda_model ondaModel
     * @return	boolean
     *  */
    public static boolean editarNotaFinal(Onda_model ondaModel){
    	
    	try {
    		
    		boolean resultado = Onda_dao.editarNotaFinal(ondaModel);
        	
        	return resultado;
        	
		} catch (Exception e) {
			
			return false;
			
		}
    	
    	
    }
	
}
