package DAO.Campeonato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Control.Campeonato.Bateria_control;
import DAO.BDConexao_dao;
import Model.Campeonato.Bateria_model;
import Model.Campeonato.Inscrito.AtletaDisputa_model;
import Model.Campeonato.Inscrito.JuizBateria_model;

public class Bateria_dao extends CampeonatoEtapa_dao{

	public Bateria_dao() {
		// TODO Auto-generated constructor stub
	}
	
	public Bateria_model carregarBateria( Bateria_model bateriaModel ){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();

    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( bateriaModel.getIdcampeonatoetapa() != 0 ){
    		String valor = " idcampeonato=";
    		valor += Integer.toString(bateriaModel.getIdcampeonatoetapa());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT * FROM `bodyboardsys`.`bateria` " + filtro;
    	
    	ResultSet res = null;
    	
    	try{
    		
    		query = (Statement) conn.createStatement();
    		res = query.executeQuery(sql);
    		
    	} catch(SQLException e){
    		System.out.println("Erro ao conectar com o banco: " + e.getMessage());
    		System.err.println("SQLException: " + e.getMessage());
    		System.err.println("SQLState: " + e.getSQLState());
    		System.err.println("VendorError: " + e.getErrorCode());
    		return null;
    	} 
    	
    	try{
    		
    		bateriaModel = Bateria_control.carregarBateriaResultSet(res);
    		conn.close();
    		return bateriaModel;
    		
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    }
	
	/**
	 * Funcao que carrega uma lista de baterias
	 * @param Bateria_model bateriaModel
	 * @param String ordenar
	 * @return ArrayList<Bateria_model>
	 * */
	public static ArrayList<Bateria_model> listarBateria(Bateria_model bateriaModel, String ordenar){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Bateria_model> listaBateria = new ArrayList<Bateria_model>();
		
		//Filtro da minha query
    	String filtro = ""; 
    	String ordem = ""; 
    	
		try{
			
			if(ordenar != null){
				ordem += BDConexao_dao.adicionaOrdem(ordem,ordenar,"ASC");
			}
			
	    	if( bateriaModel.getIdbateria() != 0 ){
	    		 
    			String valor = " idbateria=";
	    		valor += Integer.toString(bateriaModel.getIdbateria());
    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    	
	    	if( bateriaModel.getIdcampeonatoetapa() != 0 ){
	    		
	    		String valor = " idcampeonatoetapa=";
	    		valor += Integer.toString(bateriaModel.getIdcampeonatoetapa());
	    		filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    		
	    	}
	    	
			conn = BDConexao_dao.conectar();
			
			String sql = "SELECT * FROM `bodyboardsys`.`bateria` " + filtro+ " " + ordem + ";";
			
			pstmt = conn.prepareStatement(sql);	
			
			res = pstmt.executeQuery();
			
			listaBateria = Bateria_control.carregarListaBateriaResultSet(res);
			
		} catch(SQLException e){
			System.out.println("Erro ao conectar com o banco: " + e.getMessage());
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		} finally{
			try {
				pstmt.close();
				conn.close();
				conn = null;				
				pstmt = null;
				return listaBateria;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
		
	}
	
	 /**
     * Funcao que carrega uma lista de Bateria_model que um juiz deve julgar
     * @param	Bateria_model bateriaModel
     * @param	JuizBateria_model juizBateriaModel
     * @param	String ordenar
     * @return	ArrayList<Bateria_model> listaBateria
     *  */
    public static ArrayList<Bateria_model> listarBateriaJuiz( Bateria_model bateriaModel,JuizBateria_model juizBateriaModel, String ordenar ){
    	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Bateria_model> listaBateria = new ArrayList<Bateria_model>();
		
		//Filtro da minha query
		String filtro = ""; 
		String ordem = ""; 
		
		try{
			
			if(ordenar != null){
				ordem += BDConexao_dao.adicionaOrdem(ordem,ordenar,"ASC");
			}
			
			if( bateriaModel.getIdbateria() != 0 ){
				
				String valor = " idbateria=";
				valor += Integer.toString(bateriaModel.getIdbateria());
				filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
				
			}
			
			if( bateriaModel.getIdcampeonatoetapa() != 0 ){
				
				String valor = " ce.idcampeonatoetapa=";
				valor += Integer.toString(bateriaModel.getIdcampeonatoetapa());
				filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
				
			}
			
			if( bateriaModel.getNomeEtapa() != null && bateriaModel.getNomeEtapa() != "" ){
				String valor = " ce.nome like'"+bateriaModel.getNomeEtapa()+"%' ";
				filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
			}
			
			if(juizBateriaModel != null){
				if( juizBateriaModel.getIdinscricaojuiz() != 0 ){
					
					String valor = " ij.idinscricaojuiz=";
					valor += Integer.toString(juizBateriaModel.getIdinscricaojuiz());
					filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
					
				}
				
				if(juizBateriaModel.getJuizModel() != null){
					if( juizBateriaModel.getJuizModel().getIdjuiz() != 0 ){
						
						String valor = " ij.idjuiz=";
						valor += Integer.toString(juizBateriaModel.getJuizModel().getIdjuiz());
						filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
						
					}
				}				
				
			}
			
			conn = BDConexao_dao.conectar();
			
			String sql = "SELECT * FROM `bodyboardsys`.`bateria` b " +
					"JOIN `bodyboardsys`.`campeonatoetapa` ce ON (ce.idcampeonatoetapa = b.idcampeonatoetapa) " +
					"JOIN `bodyboardsys`.`inscricaojuiz` ij ON (ij.idcampeonatoetapa = ce.idcampeonatoetapa) " +
					"" + filtro+ " GROUP BY b.idbateria " + ordem + ";";
			
			pstmt = conn.prepareStatement(sql);	
			
			res = pstmt.executeQuery();
			
		} catch(SQLException e){
			   
			   try {
					conn.close();	
				    System.out.println("Erro ao conectar com o banco: " + e.getMessage());
			        System.err.println("SQLException: " + e.getMessage());
			        System.err.println("SQLState: " + e.getSQLState());
			        System.err.println("VendorError: " + e.getErrorCode());
			        
					return null;
					
				} catch (SQLException e2) {
			   		
					System.out.println("Erro ao conectar com o banco: " + e.getMessage());
			   		System.err.println("SQLException: " + e.getMessage());
			   		System.err.println("SQLState: " + e.getSQLState());
			   		System.err.println("VendorError: " + e.getErrorCode());	    		
			   		
			   		return null;
				}
				
	   }
	
	   try{
		   
		    Bateria_control bateriaControl = new Bateria_control(); 
			listaBateria = bateriaControl.carregarListaBateriaJuizResultSet(res);
			
			//fecho a conexao do BD
			conn.close();
			
			return listaBateria;
		
	   } catch (Exception e) {
			return null;
	   }
			
	}
	
	/**
     * Funcao para persistir uma bateria já montadas
     * @param	ArrayList<Bateria_model> listaBateriaModel
     * @return	boolean
     *  */
    public Bateria_model salvarBateriaMontada( Bateria_model bateriaModel ){
    	
       //Inicio as variaveis que serão utilizados para este metodo
       boolean resultado = false;
    	
       Connection conn = null;
	   PreparedStatement pstmt = null;
	   Statement stmt = null;
	   
	   try{
		   
		   conn = BDConexao_dao.conectar();
		   
		   conn.setAutoCommit(false);
		   
		   //Formato a data
		   DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   
		   String tempoInicio = "NULL";
		   String tempoEncerramento = "NULL";
		   
		   if(bateriaModel.getTempoInicio() != null){
			   tempoInicio = "'" + dataFormataData.format(bateriaModel.getTempoInicio()) + "'";
			   tempoEncerramento = "'" + dataFormataData.format(bateriaModel.getTempoEncerramento())+ "'";
		   }
		   
		   //cadastro de bateria
		   String sql = "INSERT INTO `bodyboardsys`.`bateria` " +
	   		"(`idbateria`, " +
	   		"`fase`, " +
	   		"`idcampeonatoetapa`, " +
	   		"`tempo`, " +
	   		"`intervaloTempo`, " +
	   		"`tempoInicio`, " +
	   		"`tempoEncerramento`, " +
	   		"`dataCadastro` " +
	   		") " +
	   		"VALUES " +
	   		"(NULL, " +
	   		"'"+bateriaModel.getFase()+"', " +
	   		"'"+bateriaModel.getIdcampeonatoetapa()+"', " +
	   		"'"+bateriaModel.getTempo()+"', " +
	   		"'"+bateriaModel.getIntervaloTempo()+"', " +
	   		""+tempoInicio+", " +
	   		""+tempoEncerramento+", " +
	   		"NULL);";
	   
		   stmt = conn.createStatement();
	       stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
	       ResultSet res = stmt.getGeneratedKeys();
	      
	       if ( res.next() ) {
	    	   bateriaModel.setIdbateria(res.getInt(1));   	    	       
	       }else{
	    	   conn.rollback();
	    	   return null;
	       }
	       
	   } catch(SQLException e){
		   
		   try {
				// dou um rollback no BD caso ocorra alguma excessao ao inserir o Campeonato
				conn.rollback();
				conn.close();	
			    System.out.println("Erro ao conectar com o banco: " + e.getMessage());
		        System.err.println("SQLException: " + e.getMessage());
		        System.err.println("SQLState: " + e.getSQLState());
		        System.err.println("VendorError: " + e.getErrorCode());
		        
				return null;
				
			} catch (SQLException e2) {
		   		
				System.out.println("Erro ao conectar com o banco: " + e.getMessage());
		   		System.err.println("SQLException: " + e.getMessage());
		   		System.err.println("SQLState: " + e.getSQLState());
		   		System.err.println("VendorError: " + e.getErrorCode());	    		
		   		
		   		return null;
			}
			
	   }
	
	   try{
			//dou commit no BD das alteracoes do usuario
			conn.commit();
			    		
			//fecho a conexao do BD
			conn.close();
			
			return bateriaModel;
		
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
    	
		Connection conn = null;	
		Statement stmt = null;
		int res = 0;
		
		try{
			conn = BDConexao_dao.conectar();
			conn.setAutoCommit(false);
			
			//Filtro da minha query
			String filtro = ""; 
			String sql = "";
			if( idbateria != 0 ){
				String valor = " idbateria=";
				valor += Integer.toString( idbateria );
				filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
			}
			
			if(status == 1){
				
				sql = "UPDATE `bodyboardsys`.`bateria` " +
					"SET " +
					"tempoInicio = NULL, " +
					"tempoEncerramento = NULL, " +
					"status = '"+status+"' " +
				filtro;
			
			}else if(status == 2){
				
				try {
					
					Date date = new Date();

					GregorianCalendar gc = new GregorianCalendar();  
					gc.setTime(date);  
					
					//Calendar hoje = Calendar.getInstance();
					int minuto = gc.get(Calendar.MINUTE) + 20;
					int hora = gc.get(Calendar.HOUR_OF_DAY);
					
					if( minuto >= 60 ){
						
						if( minuto == 60 ){
							hora = hora + 1;
							minuto = 00;
						}else{
							minuto = minuto - 60;
							hora = hora + 1;
							
							if(hora == 24){
								hora = 0;
							}
						}
						
					}
					
					String minutoString = Integer.toString( minuto );
					String horaString = Integer.toString( hora );
					
					if(minuto == 0){
						minutoString = "00";
					}
					if(hora == 0){
						horaString = "00";
					}
					
					
					//Formato a data
					DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					DateFormat dataFormataDataDepois = new SimpleDateFormat("yyyy-MM-dd");
					DateFormat dataFormataDataHoraDepois = new SimpleDateFormat("hh");
					   
					sql = "UPDATE `bodyboardsys`.`bateria` " +
						"SET " +
						"tempoInicio = '"+dataFormataData.format(date)+"', " +
						"tempoEncerramento = '"+dataFormataDataDepois.format(date)+" "+horaString+":"+minutoString+":00', " +
						"status = '"+status+"' " +
					filtro;
					
				} catch (Exception e) {
					
					sql = "";
					
				}
								
			}else{
				
				sql = "UPDATE `bodyboardsys`.`bateria` " +
					"SET " +
					"status = '"+status+"' " +
				filtro;
				
			}
			
			stmt = conn.createStatement();
			
			res = stmt.executeUpdate(sql);
			
		} catch(SQLException e){
			
			try {
				// dou um rollback no BD caso ocorra alguma excessao ao inserir o Campeonato
				conn.rollback();
				conn.close();	
				String erro = "Erro ao conectar com o banco: " + e.getMessage();
				erro +="SQLException: " + e.getMessage();
				erro +="SQLState: " + e.getSQLState();
				erro +="VendorError: " + e.getErrorCode();
				
				return false;
				
			} catch (SQLException e2) {
				
				String erro = "Erro ao conectar com o banco: " + e.getMessage();
				erro +="SQLException: " + e.getMessage();
				erro +="SQLState: " + e.getSQLState();
				erro +="VendorError: " + e.getErrorCode();    		
				
				return false;
			}
			
		}
		
		try{
			
			conn.commit();
			
			//fecho a conexao do BD
			conn.close();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}  	
		
	}

}
