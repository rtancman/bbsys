package br.com.bbsys.control.campeonato;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.bbsys.control.campeonato.Inscrito.AtletaDisputaController;
import br.com.bbsys.control.campeonato.Inscrito.JuizBateriaController;
import br.com.bbsys.dao.campeonato.Bateria_dao;
import br.com.bbsys.model.campeonato.Bateria_model;
import br.com.bbsys.model.campeonato.Inscrito.AtletaDisputa_model;
import br.com.bbsys.model.campeonato.Inscrito.JuizBateria_model;
import br.com.bbsys.model.usuario.Atleta_model;

public class BateriaController extends CampeonatoEtapaController {

	public BateriaController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Funcao que carrega um Bateria_model apartir da instancia atual
     * @param	Bateria_model bateriaModel
     * @return	Bateria_model bateriaModel
     *  */
    public Bateria_model carregarBateria( Bateria_model bateriaModel ){
    	    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Bateria_model bateriaModelAux = null; 
    	Bateria_dao bateriaDao = new Bateria_dao();
    	
    	bateriaModelAux = bateriaDao.carregarBateria(bateriaModel);
    	
    	return bateriaModelAux;
    	
    }

    /**
     * Funcao que carrega uma lista de Bateria_model apartir da instancia atual
     * @param	Bateria_model bateriaModel
     * @param	String ordenar
     * @return	ArrayList<Bateria_model> listaBateria
     *  */
    public ArrayList<Bateria_model> listarBateria( Bateria_model bateriaModel, String ordenar ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	ArrayList<Bateria_model> listaBateria = null; 
    	
    	listaBateria = Bateria_dao.listarBateria(bateriaModel,ordenar);
    	
    	return listaBateria;
    	
    }
    
    /**
     * Funcao que carrega uma lista de Bateria_model que um juiz deve julgar
     * @param	Bateria_model bateriaModel
     * @param	JuizBateria_model juizBateriaModel
     * @param	String ordenar
     * @return	ArrayList<Bateria_model> listaBateria
     *  */
    public ArrayList<Bateria_model> listarBateriaJuiz( Bateria_model bateriaModel,JuizBateria_model juizBateriaModel, String ordenar ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	ArrayList<Bateria_model> listaBateria = null;
    	
    	try {
    		
    		listaBateria = Bateria_dao.listarBateriaJuiz(bateriaModel,juizBateriaModel,ordenar);
    		return listaBateria;
    		
		} catch (Exception e) {
			
			return null;
			
		}
    }
    
    /**
     * Funcao para alterar um status de bateria
     * @param	int idbateria
     * @param	int status
     * @return	boolean resultdo
     *  */
    public static boolean editarStatusBateria( int idbateria,int status ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	boolean resultdo = false;
    	
    	try {
    		
    		resultdo = Bateria_dao.editarStatusBateria(idbateria,status);
    		return resultdo;
    		
    	} catch (Exception e) {
    		
    		return false;
    		
    	}
    }
        
    /**
     * Funcao que seta a instancia de Bateria_model apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	Bateria_model bateriaModel
     *  */
    public static Bateria_model carregarBateriaResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Bateria_model bateriaModel = new Bateria_model();
    	
    	try{
	    	if( res.next() ){
	    		                 
  			  /*
  			   bateriaModel.setIdcampeonatoetapa(res.getInt("idcampeonatoetapa"));
  			   bateriaModel.setIdcampeonato(res.getInt("idcampeonato"));
  			   bateriaModel.setNomeEtapa(res.getString("nome"));
  			   bateriaModel.setPraia(res.getString("praia"));       
  			   bateriaModel.setCidade(res.getString("cidade"));       
  			   bateriaModel.setIdestado(res.getInt("idestado"));       
  			   bateriaModel.setIdpais(res.getInt("idpais"));       
  			   bateriaModel.setDataEncerramentoEtapa(res.getDate("dataEncerramento"));                  
  			   bateriaModel.setDataInicioEtapa(res.getDate("dataInicio"));                  
  			   bateriaModel.setDataCadastroEtapa(res.getDate("dataCadastro"));
  			   */
  			   //bateria model
  			   bateriaModel.setIdbateria(res.getInt("idbateria"));                  
 			   bateriaModel.setFase(res.getInt("fase"));                  
 			   bateriaModel.setTempo(res.getInt("tempo"));                  
 			   bateriaModel.setIntervaloTempo(res.getInt("intervaloTempo"));                  
 			   bateriaModel.setTempoInicio(res.getDate("tempoInicio"));                  
 			   bateriaModel.setTempoEncerramento(res.getDate("tempoEncerramento"));                  
 			   bateriaModel.setDataCadastroBateria(res.getDate("dataCadastro"));   
			}
	    	return bateriaModel;
	    	
    	}catch (Exception e) {
    		return null;
		}	
    	
    }
    
    /**
     * Funcao que retorna um arrayList<bateriaModel_model> apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	ArrayList<bateriaModel_model>
     *  */
    public static ArrayList<Bateria_model> carregarListaBateriaResultSet( ResultSet res ){
    	
    	//Inicio as variaveis que ser�o utilizados para este metodo
    	ArrayList<Bateria_model> listabateriaModel = null;
    	Bateria_model bateriaModel = null;
    	
    	try{
    		listabateriaModel = new ArrayList<Bateria_model>();
    		while(res.next()){
    			
    		   bateriaModel = new Bateria_model();
 			   /*
 			   bateriaModel.setIdcampeonatoetapa(res.getInt("idcampeonatoetapa"));
 			   bateriaModel.setIdcampeonato(res.getInt("idcampeonato"));
 			   bateriaModel.setNomeEtapa(res.getString("nome"));
 			   bateriaModel.setPraia(res.getString("praia"));       
 			   bateriaModel.setCidade(res.getString("cidade"));       
 			   bateriaModel.setIdestado(res.getInt("idestado"));       
 			   bateriaModel.setIdpais(res.getInt("idpais"));       
 			   bateriaModel.setDataEncerramentoEtapa(res.getDate("dataEncerramento"));                  
 			   bateriaModel.setDataInicioEtapa(res.getDate("dataInicio"));                  
 			   bateriaModel.setDataCadastroEtapa(res.getDate("dataCadastro"));
 			   */
 			   //bateria model
 			   bateriaModel.setIdbateria(res.getInt("idbateria"));                  
			   bateriaModel.setFase(res.getInt("fase"));                  
			   bateriaModel.setTempo(res.getInt("tempo"));                  
			   bateriaModel.setIntervaloTempo(res.getInt("intervaloTempo"));                  
			   bateriaModel.setTempoInicio(res.getDate("tempoInicio"));                  
			   bateriaModel.setTempoEncerramento(res.getDate("tempoEncerramento"));                  
			   bateriaModel.setDataCadastroBateria(res.getDate("dataCadastro"));
 			   listabateriaModel.add(bateriaModel);
 			   
    		}
    		return listabateriaModel;
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }
    
    
    /**
     * Funcao que retorna um arrayList<bateriaModel_model> que um juiz deve julgar apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	ArrayList<bateriaModel_model>
     *  */
    public ArrayList<Bateria_model> carregarListaBateriaJuizResultSet( ResultSet res ){
    	
    	//Inicio as variaveis que ser�o utilizados para este metodo
    	ArrayList<Bateria_model> listabateriaModel = null;
    	Bateria_model bateriaModel = null;
    	
    	try{
    		listabateriaModel = new ArrayList<Bateria_model>();
    		while(res.next()){
    			
    			bateriaModel = new Bateria_model();
    		   //dados da etapa
 			   bateriaModel.setIdcampeonatoetapa(res.getInt("ce.idcampeonatoetapa"));
 			   bateriaModel.setIdcampeonato(res.getInt("ce.idcampeonato"));
 			   bateriaModel.setNomeEtapa(res.getString("ce.nome"));
 			   bateriaModel.setPraia(res.getString("ce.praia"));       
 			   bateriaModel.setCidade(res.getString("ce.cidade"));       
 			   //bateriaModel.setIdestado(res.getInt("ce.idestado"));       
 			   //bateriaModel.setIdpais(res.getInt("ce.idpais"));       
 			   bateriaModel.setDataEncerramentoEtapa(res.getDate("ce.dataEncerramento"));                  
 			   bateriaModel.setDataInicioEtapa(res.getDate("ce.dataInicio"));                  
 			   bateriaModel.setDataCadastroEtapa(res.getDate("ce.dataCadastro"));
 			   //bateria model
 			   bateriaModel.setIdbateria(res.getInt("b.idbateria"));                  
 			   bateriaModel.setFase(res.getInt("b.fase"));                  
 			   bateriaModel.setTempo(res.getInt("b.tempo"));                  
 			   bateriaModel.setIntervaloTempo(res.getInt("b.intervaloTempo"));                  
 			   bateriaModel.setTempoInicio(res.getDate("b.tempoInicio"));                  
 			   bateriaModel.setTempoEncerramento(res.getDate("b.tempoEncerramento"));                  
 			   bateriaModel.setDataCadastroBateria(res.getDate("b.dataCadastro"));
 			   bateriaModel.setStatus(res.getInt("b.status"));
 			   //carrego a lista de atletas de uma bateria
 			   bateriaModel.setListaAtletaDisputa( listarAtletasBateria(bateriaModel) );
 			   
 			   listabateriaModel.add(bateriaModel);
    			
    		}
    		return listabateriaModel;
    	}catch (Exception e) {
    		return null;
    	}	
    	
    }
    
    /**
     * Funcao que embaralha os atletas para montar uma bateria
     * @param	ArrayList<Atleta_model>
     * @return	ArrayList<Atleta_model>
     *  */
    public ArrayList<Atleta_model> misturarAtletas( ArrayList<Atleta_model> listaAtletaInscrito ){
    	
    	try{
    		
    		//misturo a lista de atleta para formar as baterias
			 Collections.shuffle(listaAtletaInscrito);
			 
    	}catch (Exception e) {
    		return null;
    	}	
    	
    	return listaAtletaInscrito;
    }
    
    /**
     * Funcao que monta as baterias de uma etapa de campeonato
     * @param	Bateria_model bateriaModel
     * @param	ArrayList<Atleta_model> listaAtletaInscrito
     * @return	ArrayList<Bateria_model>
     *  */
    public ArrayList<Bateria_model> montarBateria( Bateria_model bateriaModel, ArrayList<Atleta_model> listaAtletaInscrito ){
    	
    	//Inicio as variaveis que ser�o utilizados para este metodo
    	ArrayList<Bateria_model> listabateriaModel = new ArrayList<Bateria_model>();
    	ArrayList<AtletaDisputa_model> listaAtletaDisputaModel = null;
    	AtletaDisputa_model atletaDisputaModel = null;
    	Atleta_model atletaModelAux = null;
    	Bateria_model bateriaModelAux = null;
    	int contador = 0;
    	int posicaoContador = 0;
    	
    	//pego os valores da instancia de bateriaModel
    	int campeonatoEtapaId = bateriaModel.getIdcampeonatoetapa();
    	int fase = bateriaModel.getFase();
    	int tempo = bateriaModel.getTempo();
    	int tempoIntervalo = bateriaModel.getIntervaloTempo();
    	Date dataInicio = bateriaModel.getDataInicio();
    	Date dataEncerramento = bateriaModel.getDataEncerramento();
		 
    	try{
    		
    		//checo se poderemos montar esta bateria
    		int totalDeAtletas = listaAtletaInscrito.size();
        	int resto = totalDeAtletas%4;
        	
        	if(resto != 0){
        		return null;
        	}
    		
    		//misturo os atletas
    		this.misturarAtletas(listaAtletaInscrito);
    		    		
    		String arrayAux[] = new String[4];
    		arrayAux[0] = "Branca";
    		arrayAux[1] = "Verde";
    		arrayAux[2] = "Preta";
    		arrayAux[3] = "Vermelha";
    		
    		//enquanto tenho lista de atleta monto as baterias
    		while( listaAtletaInscrito.size() != 0 ){
	    		
    			for(int i = 0; i <= totalDeAtletas; i++){
    				
    				//se for a  primeira iteracao instancio um obj Bateria_model
	    			if(contador == 0){
	    				
	    				//instancio a lista de AtletaDisputa_model 
	    				listaAtletaDisputaModel = new ArrayList<AtletaDisputa_model>();
	    				
	    				//instancio o obj Bateria_model
	    				bateriaModelAux = new Bateria_model(fase,tempo,tempoIntervalo,dataInicio,dataEncerramento,listaAtletaDisputaModel);
	    				
	    				//seto o id deste campeonatoEtapa
	    				bateriaModelAux.setIdcampeonatoetapa(campeonatoEtapaId);
	    				
	    			}
	    			
	    			//se ja tenho uma lista de 4 atletas monto a primeira bateria e adiciono na lista
	    			if( bateriaModelAux.getListaAtletaDisputa().size() == 4 ){
	    				
	    				//preecho a lista de bateria
	    				listabateriaModel.add(bateriaModelAux);

	    				contador = 0;
	    				posicaoContador = 0;
	    				
	    				//saio deste loop
	    				break;
	    			}
	    			
	    			//caso entre em loop infinito utilizo o meu contador de seguranca para sair deste loop
	    			if(posicaoContador > 4){
	    				listabateriaModel = null;
	    				break;
	    			}
	    			
    				//pego o atleta da lista 
    				atletaModelAux = listaAtletaInscrito.get(0);
    				
	    			//instancio a disputa de atleta
	    			atletaDisputaModel = new AtletaDisputa_model(atletaModelAux,bateriaModelAux,arrayAux[contador]);
					
	    			//seto o atletaDisputaModel no atributo listaAtletaDisputa da bateria
	    			bateriaModelAux.getListaAtletaDisputa().add(atletaDisputaModel);
	    			
	    			//tiro da lista o atleta que acabei de adicionar
	    			listaAtletaInscrito.remove(0);
	    			
	    			//incremento os contadores
	    			posicaoContador ++;
	    			contador ++;
	    			
	    		}
    			
    		}
    		
    	}catch (Exception e) {
    		return null;
    	}	
    	
    	return listabateriaModel;
    }
    
    /**
     * Funcao para persistir uma lista de baterias j� montadas
     * @param	ArrayList<Bateria_model> listaBateriaModel
     * @param	ArrayList<JuizBateria_model> listaJuizInscritoBateriaModel
     * @return	boolean
     *  */
    public boolean gravarListaDeBateriaMontada( ArrayList<Bateria_model> listaBateriaModel, ArrayList<JuizBateria_model> listaJuizInscritoBateriaModel ){
    	
    	//Inicio as variaveis que ser�o utilizados para este metodo
    	boolean resultado = false;
    	Bateria_model bateriaModel = null;
    	
    	try{
    		
    		Bateria_dao bateriaDao = new Bateria_dao();
    		
    		for(Bateria_model bateriaModelAux : listaBateriaModel){
    			
    			//primeiro gravo a bateria 
    			bateriaModelAux = bateriaDao.salvarBateriaMontada(bateriaModelAux);
    			
    			//instancio o controlador do obj AtletaDisputa
    			AtletaDisputaController atletaDisputaControl = new AtletaDisputaController();
    			JuizBateriaController juizBateriaControl = new JuizBateriaController();
    			
    			if(bateriaModelAux != null){
    				
    				//depois gravo os atletas disputas
    				for(AtletaDisputa_model atletaDisputaModelAux : bateriaModelAux.getListaAtletaDisputa() ){
    					
    					atletaDisputaModelAux.setBateriaModel(bateriaModelAux);
    					
    					resultado = atletaDisputaControl.salvarAtletaDisputa(atletaDisputaModelAux);
    					
    					if(resultado == false){
    						break;
    					}
    				}
    				
    				//gravo a lista de juizes para esta bateria
    				for(JuizBateria_model juizBateriaModelAux : listaJuizInscritoBateriaModel ){
    					
    					juizBateriaModelAux.setBateriaModel(bateriaModelAux);
    					
    					//resultado = atletaDisputaControl.salvarAtletaDisputa(juizBateriaModelAux);
    					resultado = juizBateriaControl.salvarJuizBateria(juizBateriaModelAux);
    					
    					if(resultado == false){
    						break;
    					}
    				}
    				
    			}else{
    				break;
    			}
    			
    		}
    	/*
    		//caso de algum problema deleto os registros do BD
        	if(resultado == false){
        		
    			for(Bateria_model bateriaModelAux : listaBateriaModel){
    			    			
    				//primeiro gravo a bateria 
    				bateriaModelAux = bateriaDao.salvarBateriaMontada(bateriaModelAux);
    					
    				atletaDisputaControl.deletarTodosAtletaDisputa(atletaDisputaModelAux);
    				
    			}
        	}
    		*/
    	}catch (Exception e) {
    		return false;
    	}	
    	
    	return resultado;
    }
	

    /**
     * Funcao que monta uma lista com todas as chaves da bateria
     * @param	Bateria_model bateriaModel
     * @return	ArrayList<Bateria_model>
     * */
    public ArrayList<Bateria_model> listarBateriaChaves( Bateria_model bateriaModel ){
    	
    	//Inicio as variaveis que ser�o utilizados para este metodo
    	ArrayList<Bateria_model> listabateriaModel = null;
    	ArrayList<AtletaDisputa_model> listaAtletaDisputaModel = null;
    	AtletaDisputa_model atletaDisputaModel = null;
    	AtletaDisputaController atletaDisputaControl = null;
    	String ordem = "fase";
    	
    	try{
    		
    		//Inicio os objetos que ser�o utilizados para este metodo
        	listabateriaModel = Bateria_dao.listarBateria(bateriaModel,ordem);
        	atletaDisputaControl = new AtletaDisputaController();
        	
        	for(Bateria_model bateriaModelAux : listabateriaModel ){
        		
        		atletaDisputaModel = new AtletaDisputa_model();
        		atletaDisputaModel.setBateriaModel(bateriaModelAux);
        		//carrego alista de atletas que vao disputar essa bateria
        		listaAtletaDisputaModel = atletaDisputaControl.listarAtletaDisputa(atletaDisputaModel);
        		
        		if(listaAtletaDisputaModel != null){
        			bateriaModelAux.setListaAtletaDisputa(listaAtletaDisputaModel);
        		}
        		
    		}      	    	
        	
    				
    	}catch (Exception e) {
    		return null;
    	}	
    	
    	return listabateriaModel;
    }
    
    /**
     * Funcao que monta uma lista com todas os atletas participantes de uma bateria
     * @param	Bateria_model bateriaModel
     * @return	ArrayList<AtletaDisputa_model>
     * */
    public ArrayList<AtletaDisputa_model> listarAtletasBateria( Bateria_model bateriaModel ){
    	
    	//Inicio as variaveis que ser�o utilizados para este metodo
    	ArrayList<AtletaDisputa_model> listaAtletaDisputaModel = null;
    	AtletaDisputa_model atletaDisputaModel = null;
    	AtletaDisputaController atletaDisputaControl = null;
    	
    	try{
    		
			atletaDisputaModel = new AtletaDisputa_model();
			atletaDisputaModel.setBateriaModel(bateriaModel);
			
			//carrego alista de atletas que vao disputar essa bateria
			atletaDisputaControl = new AtletaDisputaController();
			listaAtletaDisputaModel = atletaDisputaControl.listarAtletaDisputa(atletaDisputaModel);

			return listaAtletaDisputaModel;
			
    	}catch (Exception e) {
    		return null;
    	}	
    	
    
    }
    
    /**
     * Funcao para pegar o tempo restante de uma bateria
     * @param Date tempoEncerramento
     * @return int idadeAux
     *  */
    public int pegarTempoBateria(Date tempoEncerramento){
    	
		GregorianCalendar gc = new GregorianCalendar();  
		gc.setTime(tempoEncerramento);  
		
		Calendar hoje = Calendar.getInstance();
		
		int tempoRestante = hoje.get(Calendar.MINUTE);
			
    	return tempoRestante;
    	
    }
    
}
