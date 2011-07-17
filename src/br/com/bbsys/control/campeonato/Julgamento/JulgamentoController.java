package br.com.bbsys.control.campeonato.Julgamento;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.bbsys.control.campeonato.Inscrito.AtletaDisputaController;
import br.com.bbsys.dao.campeonato.Julgamento.Julgamento_dao;
import br.com.bbsys.model.campeonato.Bateria_model;
import br.com.bbsys.model.campeonato.Inscrito.AtletaDisputa_model;
import br.com.bbsys.model.campeonato.Inscrito.JuizBateria_model;
import br.com.bbsys.model.campeonato.Julgamento.Julgamento_model;
import br.com.bbsys.model.campeonato.Julgamento.Onda_model;

public class JulgamentoController {

    /**
     * Funcao que monta uma lista de julgamanento
     * @param	Julgamento_model julgamentoModel
     * @return	ArrayList<Julgamento_model>
     * */
	public ArrayList<Julgamento_model> listar( Julgamento_model julgamentoModel, String ordenar ){
		
		ArrayList<Julgamento_model> listaJulgamento = null;
		
		try {
			
			listaJulgamento = Julgamento_dao.listarJulgamento(julgamentoModel, ordenar);
			
			return listaJulgamento;

		} catch (Exception e) {
		
			return null;
			
		}
		
	}
	
	/**
	 * Funcao que para montar um ranking da bateria apartir das notas lancadas
	 * @param	Julgamento_model julgamentoModel
	 * @return	ArrayList<Julgamento_model>
	 * */
	public ArrayList<Bateria_model> rankingJulgamentoBateria( ArrayList<Bateria_model> listaBateria ){
		
		try {
			
			for( Bateria_model bateriaModelAux : listaBateria ){
				
				try {
					// Em ordem decrescente do inicio do mandato
			        Collections.sort( bateriaModelAux.getListaAtletaDisputa() , new Comparator() {
			            public int compare(Object o1, Object o2) {
			            	AtletaDisputa_model a1 = (AtletaDisputa_model) o1;
			            	AtletaDisputa_model a2 = (AtletaDisputa_model) o2;
			                return a1.getNotaFinal() < a2.getNotaFinal() ? +1 : (a1.getNotaFinal() > a2.getNotaFinal() ? -1 : 0);
			            }
			        });
				} catch (Exception e) {
					break;
				}		        
			}
			
			return listaBateria;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	/**
    * Funcao que carrega uma lista de Julgamento_model apartir de um obj ResultSet
    * @param	ResultSet res
    * @return	ArrayList<Julgamento_model>
    **/
   public ArrayList<Julgamento_model> carregarListaJulgamento( ResultSet res ){
   	
   	   //Inicio os objetos que ser�o utilizados para este metodo
	   ArrayList<Julgamento_model> listaJulgamento = null;
	   JuizBateria_model juizBateriaModel = null;
	   Julgamento_model julgamentoModel = null;
	   Onda_model ondaModel = null;
	   //Bateria_model bateriaModel = null;
	 
	   try{
		   
		   listaJulgamento = new ArrayList<Julgamento_model>();
	    	while(res.next()){
	    		
	    		//obj julgamentoModel
	    		julgamentoModel = new Julgamento_model();
	    		julgamentoModel.setIdjulgamento(res.getInt("idjulgamento"));
	    		julgamentoModel.setNota(res.getDouble("nota"));
	    		julgamentoModel.setDataCadastro(res.getDate("dataCadastro"));
	    		  
			    //obj juizBateria
	    		juizBateriaModel = new JuizBateria_model();
	    		juizBateriaModel.setIdjuizbateria(res.getInt("idjuizbateria"));
	    		julgamentoModel.setJuizBateriaModel(juizBateriaModel);
	    		
	    		//obj onda
	    		ondaModel = new Onda_model();
	    		ondaModel.setIdonda(res.getInt("idonda"));
	    		julgamentoModel.setOndaModel(ondaModel);
	    		
			    listaJulgamento.add(julgamentoModel);
			}
	    	
	    	return listaJulgamento;
	    	
	   	}catch (Exception e) {
	   		return null;
		}	
   	
   }
   
   /**
    * Funcao que carrega uma lista de Julgamento_model com as notas finais apartir de um obj ResultSet
    * @param	ResultSet res
    * @return	ArrayList<Julgamento_model>
    **/
   public ArrayList<Julgamento_model> carregarListaNotasFinaisResultSet( ResultSet res ){
	   
	   //Inicio os objetos que ser�o utilizados para este metodo
	   ArrayList<Julgamento_model> listaJulgamento = null;
	   Julgamento_model julgamentoModel = null;
	   Onda_model ondaModel = null;
	   
	   try{
		   
		   listaJulgamento = new ArrayList<Julgamento_model>();
		   while(res.next()){
			   
			   //obj julgamentoModel
			   julgamentoModel = new Julgamento_model();
			   julgamentoModel.setIdjulgamento(res.getInt("j.idjulgamento"));
			   
			   //obj onda
			   ondaModel = new Onda_model();
			   ondaModel.setIdonda(res.getInt("j.idonda"));
			   ondaModel.setOrdem(res.getInt("o.ordem"));
			   ondaModel.setNotaParcial(res.getDouble("ondanotafinal"));
			   
			   //seto o valor parcial da onda no bd
			   OndaController.editarNotaFinal(ondaModel);
			   
			   julgamentoModel.setOndaModel(ondaModel);
			   
			   listaJulgamento.add(julgamentoModel);
		   }
		   
		   return listaJulgamento;
		   
	   }catch (Exception e) {
		   return null;
	   }	
	   
   }
   
   /**
    * Funcao que carrega uma lista de Julgamento_model apartir de um obj ResultSet
    * @param	ResultSet res
    * @return	ArrayList<Julgamento_model>
    **/
   public ArrayList<Julgamento_model> carregarListaNotasParciais( ResultSet res ){
   	
   	   //Inicio os objetos que ser�o utilizados para este metodo
	   ArrayList<Julgamento_model> listaJulgamento = null;
	   JuizBateria_model juizBateriaModel = null;
	   Julgamento_model julgamentoModel = null;
	   Onda_model ondaModel = null;
	   //Bateria_model bateriaModel = null;
	 
	   try{
		   
		   listaJulgamento = new ArrayList<Julgamento_model>();
	    	while(res.next()){
	    		
	    		//obj julgamentoModel
	    		julgamentoModel = new Julgamento_model();
	    		julgamentoModel.setIdjulgamento(res.getInt("j.idjulgamento"));
	    		julgamentoModel.setNota(res.getDouble("j.nota"));
	    		julgamentoModel.setDataCadastro(res.getDate("j.dataCadastro"));
	    		  
			    //obj juizBateria
	    		juizBateriaModel = new JuizBateria_model();
	    		juizBateriaModel.setIdjuizbateria(res.getInt("j.idjuizbateria"));
	    		julgamentoModel.setJuizBateriaModel(juizBateriaModel);
	    		
	    		//obj onda
	    		ondaModel = new Onda_model();
	    		ondaModel.setIdonda(res.getInt("o.idondas"));
	    		julgamentoModel.setOndaModel(ondaModel);
	    		
			    listaJulgamento.add(julgamentoModel);
			}
	    	
	    	return listaJulgamento;
	    	
	   	}catch (Exception e) {
	   		return null;
		}	
   	
   }
      
   
   /**
    * Funcao que lista as notas que o juiz deu aos atletas de uma bateria
    * @param	ArrayList<Bateria_model> listaBateria
    * @param	Julgamento_model julgamentoModel
    * @param	String ordenar
    * @return	ArrayList<Bateria_model> listaBateria
    * */
	public ArrayList<Bateria_model> listarNotasParciais( ArrayList<Bateria_model> listaBateria, Julgamento_model julgamentoModel, String ordenar ){
		
		//variaveis locais
		//ArrayList<Bateria_model> listaBateriaAux = null;
		ArrayList<Julgamento_model> listaJulgamentoAux = null;
		Onda_model ondaModel = null;
		
		try {
			
			for( Bateria_model bateriaModelAux : listaBateria ){
				
				for( AtletaDisputa_model atletaDisputaAux : bateriaModelAux.getListaAtletaDisputa() ){
					
					//carrego as ondas que cada atleta surfou
					ondaModel = new Onda_model();
					ondaModel.setAtletaDisputaModel(atletaDisputaAux);
									
					julgamentoModel.setOndaModel(ondaModel);
					listaJulgamentoAux = new ArrayList<Julgamento_model>();
					
					listaJulgamentoAux = Julgamento_dao.listarNotasParciais(julgamentoModel, ordenar);
					
					atletaDisputaAux.setListaJulgamentoModel(listaJulgamentoAux);
					
				}
				
			}
			//listaJulgamento = Julgamento_dao.listarJulgamento(julgamentoModel, ordenar);

			return listaBateria;

		} catch (Exception e) {
		
			return null;
			
		}
		
	}
	
	/**
	 * Funcao para cadastro de notas de um julgamento que um atleta surfou
	 * @param Julgamento_model julgamentoModel
	 * @return boolean
	 * */
	public boolean cadastrarNotaJulgamento (Julgamento_model julgamentoModel){
		
		boolean resposta = false;
		
		try{
		
			Julgamento_dao julgamentoDao = new Julgamento_dao();
			
			resposta = julgamentoDao.cadastrarNotaJulgamento(julgamentoModel);
			
			return resposta;
			
		}catch (Exception e) {
			
			e.getMessage();
			return false;
			
		}
		
	}
	
	/**
	 * Funcao para atualiza as notas de um julgamento de uma onda que o atleta surfou
	 * @param Julgamento_model julgamentoModel
	 * @return boolean
	 * */
	public boolean atualizarNotaJulgamento (Julgamento_model julgamentoModel){
		
		boolean resposta = false;
		
		try{
			
			Julgamento_dao julgamentoDao = new Julgamento_dao();
			
			resposta = julgamentoDao.atualizarNotaJulgamento(julgamentoModel);
			
			return resposta;
			
		}catch (Exception e) {
			
			e.getMessage();
			return false;
			
		}
		
	}
	

	   /**
	    * Funcao que lista as notas finais de cada onda o juiz deu aos atletas de uma bateria
	    * @param	ArrayList<Bateria_model> listaBateria
	    * @param	Julgamento_model julgamentoModel
	    * @param	String ordenar
	    * @return	ArrayList<Bateria_model> listaBateria
	    * */
		public ArrayList<Bateria_model> listarNotasFinais( ArrayList<Bateria_model> listaBateria, int limit ){
			
			//variaveis locais
			//ArrayList<Bateria_model> listaBateriaAux = null;
			ArrayList<Julgamento_model> listaJulgamentoAux = null;
			Onda_model ondaModel = null;
			
			try {
				
				for( Bateria_model bateriaModelAux : listaBateria ){
					
					for( AtletaDisputa_model atletaDisputaAux : bateriaModelAux.getListaAtletaDisputa() ){
						
						listaJulgamentoAux = new ArrayList<Julgamento_model>();
						
						listaJulgamentoAux = Julgamento_dao.listarNotasFinais( atletaDisputaAux.getIdatletadisputa(),limit );
						
						atletaDisputaAux.setListaJulgamentoModel(listaJulgamentoAux);
						
					}
					
				}
				
				return listaBateria;

			} catch (Exception e) {
			
				return null;
				
			}
			
		}
		
		/**
		 * Funcao que retorna e salva a nota final de cada atleta
		 * @param	ArrayList<Bateria_model> listaBateria
		 * @return	ArrayList<Bateria_model> listaBateria
		 * */
		public ArrayList<Bateria_model> listarNotasFinaisAtleta( ArrayList<Bateria_model> listaBateria ){
			
			//variaveis locais
			//ArrayList<Bateria_model> listaBateriaAux = null;
			ArrayList<Julgamento_model> listaJulgamentoAux = null;
			double nota = 0;
			int contador = 0;
			
			try {
				
				for( Bateria_model bateriaModelAux : listaBateria ){
					
					for( AtletaDisputa_model atletaDisputaAux : bateriaModelAux.getListaAtletaDisputa() ){
						
						for( Julgamento_model julgamentoModelAux : atletaDisputaAux.getListaJulgamentoModel() ){
						
							nota += julgamentoModelAux.getOndaModel().getNotaParcial();
							contador++;
							
							if(contador == 3){
								break;
							}
						}
						
						if(nota == 0){
							atletaDisputaAux.setNotaFinal(nota);
						}else{
							
							DecimalFormat aproximador = new DecimalFormat( " 0.00 " );
							
							String notaAux = aproximador.format(nota / 3);
							notaAux = notaAux.replaceAll(",", "."); 
							nota = Double.parseDouble(notaAux);
							atletaDisputaAux.setNotaFinal( nota );
							
							//gravo esta nota para este atleta
							AtletaDisputaController.editarNotaAtletaDisputa(atletaDisputaAux);
							nota = 0;
							notaAux = "";
							contador = 0;
						}
						
					}
					
				}
				
				return listaBateria;
				
			} catch (Exception e) {
				
				return null;
				
			}
			
		}
		
	
	
}
