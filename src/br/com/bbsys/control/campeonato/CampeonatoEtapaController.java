package br.com.bbsys.control.campeonato;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.bbsys.dao.campeonato.CampeonatoEtapa_dao;
import br.com.bbsys.dao.campeonato.Campeonato_dao;
import br.com.bbsys.model.campeonato.Bateria_model;
import br.com.bbsys.model.campeonato.CampeonatoEtapa_model;
import br.com.bbsys.model.campeonato.Campeonato_model;
import br.com.bbsys.model.usuario.Atleta_model;
import br.com.bbsys.model.usuario.Juiz_model;

public class CampeonatoEtapaController extends CampeonatoController {
	
	public static ArrayList<Campeonato_model> relatorioCampeonato(){
		
		ArrayList<Campeonato_model> listaCampeonato = null;
		
		listaCampeonato = Campeonato_dao.relatorioCampeonato();
		
		return listaCampeonato;
		
	}
	
	public static ArrayList<CampeonatoEtapa_model> listar(CampeonatoEtapa_model campeonatoEtapa,String tipoOrdem){
		
		ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa = null;
		
		listaCampeonatoEtapa = CampeonatoEtapa_dao.listar(campeonatoEtapa,tipoOrdem);
		
		return listaCampeonatoEtapa;
		
	}
	
	public boolean cadastrarCampeonatoEtapa( CampeonatoEtapa_model campeonatoEtapa ){
		
		boolean resultado = false;
		CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
		
		resultado = campeonatoEtapaDao.cadastrarCampeonatoEtapa(campeonatoEtapa);
		
		return resultado;
		
	}
	
	public Campeonato_model cadastrarCampeonatoInteiro( Campeonato_model campeonatoModel, CampeonatoEtapa_model campeonatoEtapaModel ){
		
		Campeonato_model campeonatoModelAux = null;
		CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
		
		campeonatoModelAux = campeonatoEtapaDao.cadastrarCampeonatoInteiro(campeonatoModel,campeonatoEtapaModel);
		
		return campeonatoModelAux;
		
	}
	
	/**
     * Funcao que carrega um CampeonatoEtapa_model apartir da instancia atual
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
    public CampeonatoEtapa_model carregarEtapa( CampeonatoEtapa_model campeonatoEtapaModel ){
    	    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_model campeonatoEtapaModelAux = null; 
    	CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
    	
    	campeonatoEtapaModelAux = campeonatoEtapaDao.carregarEtapa(campeonatoEtapaModel);
    	
    	return campeonatoEtapaModelAux;
    	
    }
    
    /**
     * Funcao que carrega um CampeonatoEtapa_model e o Campeonato_model a partir da instancia atual
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
    public CampeonatoEtapa_model carregarCampeonatoMaisEtapa( CampeonatoEtapa_model campeonatoEtapaModel ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_model campeonatoEtapaModelAux = null; 
    	CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
    	
    	campeonatoEtapaModelAux = campeonatoEtapaDao.carregarCampeonatoMaisEtapa(campeonatoEtapaModel);
    	
    	return campeonatoEtapaModelAux;
    	
    }
    
    /**
     * Funcao que seta a instancia de CampeonatoEtapa_model apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
    public static CampeonatoEtapa_model carregarEtapaResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_model campeonatoEtapaModel = new CampeonatoEtapa_model();
    	
    	DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	
    	try{
	    	if( res.next() ){
    			campeonatoEtapaModel = new CampeonatoEtapa_model();
    			campeonatoEtapaModel.setIdcampeonatoetapa(res.getInt("idcampeonatoetapa"));
    			campeonatoEtapaModel.setIdcampeonato(res.getInt("idcampeonato"));
    			campeonatoEtapaModel.setNomeEtapa(res.getString("nome"));
  			    campeonatoEtapaModel.setPraia(res.getString("praia"));       
  			    campeonatoEtapaModel.setCidade(res.getString("cidade"));       
  			    campeonatoEtapaModel.setIdestado(res.getInt("idestado"));       
  			    campeonatoEtapaModel.setIdpais(res.getInt("idpais"));
  			    
  			    //datas formato para o padrao desejado antes de setr no obj
  			    String DataEncerramentoEtapa = res.getString("dataEncerramento");
  			    String DataInicioEtapa = res.getString("dataInicio");
  			    String DataCadastroEtapa = res.getString("dataCadastro");
  			    
  			    campeonatoEtapaModel.setDataEncerramentoEtapa(dataFormataData.parse(DataEncerramentoEtapa));                  
  			    campeonatoEtapaModel.setDataInicioEtapa(dataFormataData.parse(DataInicioEtapa));                  
  			    campeonatoEtapaModel.setDataCadastroEtapa(dataFormataData.parse(DataCadastroEtapa));                  
  			      
  			    
			}
	    	return campeonatoEtapaModel;
	    	
    	}catch (Exception e) {
    		return null;
		}	
    	
    }
    
    /**
     * Funcao que seta a instancia de CampeonatoEtapa_model mais Campeonato_model apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
    public static CampeonatoEtapa_model carregarCampeonatoMaisEtapaResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_model campeonatoEtapaModel = new CampeonatoEtapa_model();
    	
    	try{
    		if( res.next() ){
    			campeonatoEtapaModel = new CampeonatoEtapa_model();
    			//etapa do campeonato
    			campeonatoEtapaModel.setIdcampeonatoetapa(res.getInt("ce.idcampeonatoetapa"));
    			campeonatoEtapaModel.setIdcampeonato(res.getInt("ce.idcampeonato"));
    			campeonatoEtapaModel.setNomeEtapa(res.getString("ce.nome"));
    			campeonatoEtapaModel.setPraia(res.getString("ce.praia"));       
    			campeonatoEtapaModel.setCidade(res.getString("ce.cidade"));       
    			campeonatoEtapaModel.setIdestado(res.getInt("ce.idestado"));       
    			campeonatoEtapaModel.setIdpais(res.getInt("ce.idpais"));       
    			campeonatoEtapaModel.setDataEncerramentoEtapa(res.getDate("ce.dataEncerramento"));                  
    			campeonatoEtapaModel.setDataInicioEtapa(res.getDate("ce.dataInicio"));                  
    			campeonatoEtapaModel.setDataCadastroEtapa(res.getDate("ce.dataCadastro"));                  
    			//campeonato
    			campeonatoEtapaModel.setIdcampeonato(res.getInt("c.idcampeonato"));
    			campeonatoEtapaModel.setNome(res.getString("c.nome"));
    			campeonatoEtapaModel.setDataInicio(res.getDate("c.dataInicio"));         
    			campeonatoEtapaModel.setDataEncerramento(res.getDate("c.dataEncerramento"));         
    			campeonatoEtapaModel.setDataCadastro(res.getDate("c.dataCadastro")); 
    		}
    		return campeonatoEtapaModel;
    		
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }
    
    /**
     * Funcao que retorna um arrayList<campeonatoEtapaModel_model> apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	ArrayList<campeonatoEtapaModel_model>
     *  */
    public static ArrayList<CampeonatoEtapa_model> carregarListaEtapaResultSet( ResultSet res ){
    	
    	//Inicio as variaveis que ser�o utilizados para este metodo
    	ArrayList<CampeonatoEtapa_model> listacampeonatoEtapaModel = null;
    	CampeonatoEtapa_model campeonatoEtapaModel = null;
    	
    	try{
    		listacampeonatoEtapaModel = new ArrayList<CampeonatoEtapa_model>();
    		while(res.next()){
    		   campeonatoEtapaModel = new CampeonatoEtapa_model();
 			   campeonatoEtapaModel.setIdcampeonatoetapa(res.getInt("idcampeonatoetapa"));
 			   campeonatoEtapaModel.setIdcampeonato(res.getInt("idcampeonato"));
 			   campeonatoEtapaModel.setNomeEtapa(res.getString("nome"));
 			   campeonatoEtapaModel.setPraia(res.getString("praia"));       
 			   campeonatoEtapaModel.setCidade(res.getString("cidade"));       
 			   campeonatoEtapaModel.setIdestado(res.getInt("idestado"));       
 			   campeonatoEtapaModel.setIdpais(res.getInt("idpais"));       
 			   campeonatoEtapaModel.setDataEncerramentoEtapa(res.getDate("dataEncerramento"));                  
 			   campeonatoEtapaModel.setDataInicioEtapa(res.getDate("dataInicio"));                  
 			   campeonatoEtapaModel.setDataCadastroEtapa(res.getDate("dataCadastro"));
 			   
 			   //carrego tb os dados deste campeonato caso se eu tenho o id dele
 			   if( campeonatoEtapaModel.getIdcampeonato() != 0 ){
 				   Campeonato_model campeonatoModel = new Campeonato_model();
 				   campeonatoModel.setIdcampeonato(campeonatoEtapaModel.getIdcampeonato());
 				   CampeonatoController campeonatoControl = new CampeonatoController();
 				   campeonatoModel = campeonatoControl.carregar(campeonatoModel);
 				   //seto o nome do campeonato
 				   campeonatoEtapaModel.setNome(campeonatoModel.getNome());
 			   }
 			   
 			   listacampeonatoEtapaModel.add(campeonatoEtapaModel);
    		}
    		return listacampeonatoEtapaModel;
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }
	
    /**
     * Funcao que edita um CampeonatoEtapa_model apartir de uma instancia
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	boolean
     *  */
    public boolean editar(CampeonatoEtapa_model campeonatoEtapaModel){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	boolean result = false;
    	CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
    	
    	result = campeonatoEtapaDao.editar( campeonatoEtapaModel );
    	
    	return result;
    	
    }
    
    /**
     * Funcao que atualiza o status de uma etpa
     * @param	int idcampeonatoEtapa
     * @param	int status
     * @return	boolean
     *  */
    public boolean editarStatusEtapa(int idcampeonatoEtapa, int status){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	boolean result = false;
    	CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
    	
    	result = campeonatoEtapaDao.editarStatusEtapa( idcampeonatoEtapa, status );
    	
    	return result;
    	
    }
    
    /**
     * Funcao que carrega uma lista de Atleta_model completo inscritos em uma etapa de campeonato
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	ArrayList<Atleta_model> listaAtletaInscrito
     **/
    public ArrayList<Atleta_model> listarAtletaInscrito( CampeonatoEtapa_model campeonatoEtapaModel ){
    	    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
    	ArrayList<Atleta_model> listaAtletaInscrito = null;
    	
    	listaAtletaInscrito = campeonatoEtapaDao.listarAtletaInscrito(campeonatoEtapaModel);
    	    	
		return listaAtletaInscrito;
    	
    }
    
    /**
     * Funcao que carrega uma lista de Juiz_model completo inscritos em uma etapa de campeonato
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	ArrayList<Juiz_model> listaJuizInscrito
     **/
    public ArrayList<Juiz_model> listarJuizInscrito( CampeonatoEtapa_model campeonatoEtapaModel ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
    	ArrayList<Juiz_model> listaJuizInscrito = null;
    	
    	listaJuizInscrito = campeonatoEtapaDao.listarJuizInscrito(campeonatoEtapaModel);
    	
    	return listaJuizInscrito;
    	
    }
    
    /**
     * Funcao que carrega um campeonato etapa inteiro com todas as baterias apartir da instancia atual de CampeonatoEtapa_model
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
    public CampeonatoEtapa_model carregarCampeonatoEtapaInteiro ( CampeonatoEtapa_model campeonatoEtapaModel ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_model campeonatoEtapaModelAux = null; 
    	CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
    	ResultSet res = null;
    	
    	try{
    		
	    	res = campeonatoEtapaDao.carregarCampeonatoEtapaInteiro(campeonatoEtapaModel);
	    	campeonatoEtapaModelAux = this.carregarCampeonatoEtapaInteiroResultSet(res);
	    	
    	}catch (Exception e) {
    		return null;
		}
    	
    	return campeonatoEtapaModelAux;
    	
    }
    
    /**
     * Funcao que carrega uma lista com todas as etapas de um campeonato e todas as baterias destas etapas apartir da instancia atual de CampeonatoEtapa_model
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
    public ArrayList<CampeonatoEtapa_model> carregarListaCampeonatoEtapaInteiro ( CampeonatoEtapa_model campeonatoEtapaModel ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa = null; 
    	CampeonatoEtapa_dao campeonatoEtapaDao = new CampeonatoEtapa_dao();
    	ResultSet res = null;
    	
    	try{
    		
	    	res = campeonatoEtapaDao.carregarCampeonatoEtapaInteiro(campeonatoEtapaModel);
	    	listaCampeonatoEtapa = this.carregarListaCampeonatoEtapaInteiroResultSet(res);
	    	
    	}catch (Exception e) {
    		return null;
		}
    	
    	return listaCampeonatoEtapa;
    	
    }
    
    /**
     * Funcao que carrega um campeonato etapa inteiro com todas as baterias apartir de um ResultSet
     * @param	ResultSet res
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
    public CampeonatoEtapa_model carregarCampeonatoEtapaInteiroResultSet ( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_model campeonatoEtapaModel = null;
    	BateriaController bateriaControl = null;
    	Bateria_model bateriaModel = null;
    	ArrayList<Bateria_model> listaBateria = null;
    	
    	try{
	    	if( res.next() ){
    			
	    		campeonatoEtapaModel = this.carregarEtapaResultSet(res);
    			
    			if(campeonatoEtapaModel.getIdcampeonatoetapa() != 0){
    				
    				//instancio o obj Bateria_model
    				bateriaModel = new Bateria_model();
    				bateriaModel.setIdcampeonatoetapa(campeonatoEtapaModel.getIdcampeonatoetapa());
    				//instancio o control do Bateria_model
    				bateriaControl = new BateriaController();
    				//carrego a lista de bateria
    				listaBateria = bateriaControl.listarBateria(bateriaModel,null);
    				//seto a lista no obj CampeonatoEtapa_model
    				campeonatoEtapaModel.setListaBateria(listaBateria);
    				
    			}else{
    				return null;
    			}
  			       
			}
	    	return campeonatoEtapaModel;
	    	
    	}catch (Exception e) {
    		return null;
		}
    	
    }
    
    /**
     * Funcao que carrega uma lista de campeonato etapa inteiro com todas as baterias apartir de um ResultSet
     * @param	ResultSet res
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
    public ArrayList<CampeonatoEtapa_model> carregarListaCampeonatoEtapaInteiroResultSet ( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	CampeonatoEtapa_model campeonatoEtapaModel = null;
    	BateriaController bateriaControl = null;
    	Bateria_model bateriaModel = null;
    	ArrayList<Bateria_model> listaBateria = null;
    	ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa = null;
    	
    	try{
    		while( res.next() ){
    			
    			campeonatoEtapaModel = this.carregarEtapaResultSet(res);
    			
    			if(campeonatoEtapaModel.getIdcampeonatoetapa() != 0){
    				
    				//instancio o obj Bateria_model
    				bateriaModel = new Bateria_model();
    				bateriaModel.setIdcampeonatoetapa(campeonatoEtapaModel.getIdcampeonatoetapa());
    				//instancio o control do Bateria_model
    				bateriaControl = new BateriaController();
    				//carrego a lista de bateria
    				listaBateria = bateriaControl.listarBateria(bateriaModel,null);
    				//seto a lista no obj CampeonatoEtapa_model
    				campeonatoEtapaModel.setListaBateria(listaBateria);
    				//adciono o objeto na lista
    				listaCampeonatoEtapa.add(campeonatoEtapaModel);
    				
    			}else{
    				
    				listaCampeonatoEtapa = null;
    				break;
    				
    			}
    			
    		}
    		return listaCampeonatoEtapa;
    		
    	}catch (Exception e) {
    		return null;
    	}
    	
    }
}
