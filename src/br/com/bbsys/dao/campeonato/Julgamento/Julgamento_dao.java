package DAO.Campeonato.Julgamento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.Campeonato.Julgamento.Julgamento_control;
import DAO.BDConexao_dao;
import Model.Campeonato.Julgamento.Julgamento_model;

public class Julgamento_dao {

	/**
	 * Funcao que carrega uma lista de julgamentos
	 * @param Julgamento_model julgamentoModel
	 * @param String ordenar
	 * @return ArrayList<Julgamento_model>
	 * */
	public static ArrayList<Julgamento_model> listarJulgamento(Julgamento_model julgamentoModel, String ordenar){
		
		ArrayList<Julgamento_model> listaJulgamento = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		//Filtro da minha query
    	String filtro = "";
    	String join = "";
    	String ordem = ""; 
    	
		try{
			
			if(ordenar != null){
				ordem += BDConexao_dao.adicionaOrdem(ordem,ordenar,"ASC");
			}
			
	    	if( julgamentoModel.getIdjulgamento() != 0 ){
	    		 
    			String valor = " idjulgamento=";
	    		valor += Integer.toString(julgamentoModel.getIdjulgamento());
    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    	
	    	if( julgamentoModel.getJuizBateriaModel() != null ){
	    		
	    		if( julgamentoModel.getJuizBateriaModel().getIdjuizbateria() != 0 ){
	    		
		    		String valor = " idjuizbateria=";
		    		valor += Integer.toString(julgamentoModel.getJuizBateriaModel().getIdjuizbateria());
		    		filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
		    		
	    		}
	    		
	    	}
	    	
			conn = BDConexao_dao.conectar();
			
			String sql = "SELECT * FROM `bodyboardsys`.`julgamento` " + filtro+ " " + ordem + ";";
			
			stmt = (Statement) conn.createStatement();
			
    		res = stmt.executeQuery(sql);
    		
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
			
			//crio a lista
			JulgamentoController julgamentoControl = new JulgamentoController();
    		listaJulgamento = julgamentoControl.carregarListaJulgamento(res);
			
			//fecho a conexao do BD
			conn.close();
			
			return listaJulgamento;
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * Funcao que carrega uma lista de julgamentos
	 * @param Julgamento_model julgamentoModel
	 * @param String ordenar
	 * @return ArrayList<Julgamento_model>
	 * */
	public static ArrayList<Julgamento_model> listarNotasParciais(Julgamento_model julgamentoModel, String ordenar){
		
		ArrayList<Julgamento_model> listaJulgamento = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		//Filtro da minha query
		String filtro = "";
		String join = "";
		String ordem = ""; 
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			if(ordenar != null && ordenar != "" ){
				ordem += BDConexao_dao.adicionaOrdem(ordem,ordenar,"ASC");
			}
			
			if( julgamentoModel.getIdjulgamento() != 0 ){
				
				String valor = " j.idjulgamento=";
				valor += Integer.toString(julgamentoModel.getIdjulgamento());
				filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
				
			}
						
			if( julgamentoModel.getJuizBateriaModel() != null ){
				
				if( julgamentoModel.getJuizBateriaModel().getIdjuizbateria() != 0 ){
					
					String valor = " j.idjuizbateria =";
					valor += Integer.toString(julgamentoModel.getJuizBateriaModel().getIdjuizbateria());
					//filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
					filtro += " WHERE " + valor;
					
				}
				
			}
			
			if( julgamentoModel.getOndaModel() != null ){
				
				if( julgamentoModel.getOndaModel().getAtletaDisputaModel() != null ){
					
					if( julgamentoModel.getOndaModel().getAtletaDisputaModel().getIdatletadisputa() != 0 ){
					
						String valor = " o.idatletadisputa = ";
						valor += Integer.toString( julgamentoModel.getOndaModel().getAtletaDisputaModel().getIdatletadisputa() );
						//filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "AND");
						filtro += " AND " + valor;
						
					}
					
				}
				
			}
			
			
			
			String sql = "SELECT * FROM `bodyboardsys`.`julgamento` j " +
					"JOIN `bodyboardsys`.`ondas` o ON (o.idondas = j.idonda) " +
					"" + filtro + " " + ordem + ";";
			
			stmt = (Statement) conn.createStatement();
			
			res = stmt.executeQuery(sql);
			
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
			
			//crio a lista
			JulgamentoController julgamentoControl = new JulgamentoController();
			listaJulgamento = julgamentoControl.carregarListaNotasParciais(res);
			
			//fecho a conexao do BD
			conn.close();
			
			return listaJulgamento;
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * Funcao que carrega uma lista de julgamentos com as notas finais
	 * @param Julgamento_model julgamentoModel
	 * @return ArrayList<Julgamento_model>
	 * */
	public static ArrayList<Julgamento_model> listarNotasFinais( int idAtletaDisputa, int limit ){
		
		ArrayList<Julgamento_model> listaJulgamento = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		String limitString = "";
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			if(limit > 0){
				String valor = Integer.toString( limit );
				limitString = "LIMIT " + valor;
			}
			
			String sql = "SELECT " +
							"j.*," +
							"o.*," +
							"ROUND((SUM(`bodyboardsys`.j.`nota`)/3), 2) as ondanotafinal " +
						 "FROM " +
						 	"`bodyboardsys`.`julgamento` j " +
						 "JOIN `bodyboardsys`.`ondas` o ON(`bodyboardsys`.o.`idondas` = `bodyboardsys`.j.`idonda`) " +
						 "WHERE " +
						 	"`bodyboardsys`.j.`idonda` in( SELECT idondas FROM `bodyboardsys`.`ondas` where idatletadisputa = '"+ idAtletaDisputa +"' ) " +
						 "GROUP BY " +
						 	"`bodyboardsys`.j.`idonda` " +
						 "ORDER BY " +
						 	"ondanotafinal DESC " +
						 limitString+";";
			
			stmt = (Statement) conn.createStatement();
			
			res = stmt.executeQuery(sql);
			
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
			
			//crio a lista
			JulgamentoController julgamentoControl = new JulgamentoController();
			listaJulgamento = julgamentoControl.carregarListaNotasFinaisResultSet(res);
			
			//fecho a conexao do BD
			conn.close();
			
			return listaJulgamento;
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * Funcao para atualiza as notas de um julgamento de uma onda que o atleta surfou
	 * @param Julgamento_model julgamentoModel
	 * @return boolean
	 * */
	public boolean atualizarNotaJulgamento (Julgamento_model julgamentoModel){
		
	   //declarocoes de variaveis	

	   Connection conn = null;
	   Statement stmt = null;
	   String filtro = "";
	   
	   try{
		   
		   conn = BDConexao_dao.conectar();
		   
		   conn.setAutoCommit(false);
		   
		   if( julgamentoModel.getOndaModel() != null ){
			   
			   if( julgamentoModel.getOndaModel().getIdonda() != 0 ){
		    		String valor = " idonda=";
		    		valor += Integer.toString(julgamentoModel.getOndaModel().getIdonda());
		    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
			   }
			   
		   }
		   
		   if( julgamentoModel.getIdjulgamento() != 0 ){
			   String valor = " idjulgamento=";
			   valor += Integer.toString(julgamentoModel.getIdjulgamento());
			   filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
		   }
		   
		   if( julgamentoModel.getJuizBateriaModel() != null ){
			   
			   if( julgamentoModel.getJuizBateriaModel().getIdjuizbateria() != 0 ){
				   String valor = " idjuizbateria=";
				   valor += Integer.toString(julgamentoModel.getJuizBateriaModel().getIdjuizbateria());
				   filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
			   }
			   
		   }
		  	
	       // monto os valores para a minha query
		   String sql = "UPDATE `bodyboardsys`.`julgamento` " +
	   		"SET " +
	   		"`nota` = '"+julgamentoModel.getNota()+"' " +
	   		filtro + ";";
		   
		   stmt = conn.createStatement();
		   stmt.executeUpdate(sql);
	      
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
	
				System.out.println("Erro ao conectar com o banco: " + e.getMessage());
		   		System.err.println("SQLException: " + e.getMessage());
		   		System.err.println("SQLState: " + e.getSQLState());
		   		System.err.println("VendorError: " + e.getErrorCode());	    		
		   		
		   		return false;
			}
			
	   }
	
	   try{

        //dou commit no BD das alteracoes
		conn.commit();   		
		
		//fecho a conexao do BD
		conn.close();
		
		return true;
						
	   } catch (Exception e) {
			return false;
	   }	      
			
	}
	
	/**
	 * Funcao para cadastro de notas de um julgamento que um atleta surfou
	 * @param Julgamento_model julgamentoModel
	 * @return boolean
	 * */
	public boolean cadastrarNotaJulgamento (Julgamento_model julgamentoModel){
		
		//declarocoes de variaveis	
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			conn.setAutoCommit(false);
			
			// monto os valores para a minha query
			String sql = "INSERT INTO `bodyboardsys`.`julgamento` " +
			"(`idjulgamento`, " +
			"`idjuizbateria`, " +
			"`idonda`, " +
			"`nota`, " +
			"`dataCadastro`) " +
			"values " +
			"(NULL, " +
			"'"+julgamentoModel.getJuizBateriaModel().getIdjuizbateria()+"', " +
			"'"+julgamentoModel.getOndaModel().getIdonda()+"', " +
			"'"+julgamentoModel.getNota()+"', " +
			"NULL);";
			
			stmt = conn.createStatement();
			stmt.execute(sql);
			
		} catch(SQLException e){
			
			try {
				// dou um rollback no BD caso ocorra alguma excessao ao inserir o Campeonato
				conn.rollback();
				conn.close();	
				System.out.println("Erro ao conectar com o banco: " + e.getMessage());
				System.err.println("SQLException: " + e.getMessage());
				System.err.println("SQLState: " + e.getSQLState());
				System.err.println("VendorError: " + e.getErrorCode());
				
				return false;
				
			} catch (SQLException e2) {
				
				System.out.println("Erro ao conectar com o banco: " + e.getMessage());
				System.err.println("SQLException: " + e.getMessage());
				System.err.println("SQLState: " + e.getSQLState());
				System.err.println("VendorError: " + e.getErrorCode());	    		
				
				return false;
			}
			
		}
		
		try{
			
			//dou commit no BD das alteracoes
			conn.commit();   		
			
			//fecho a conexao do BD
			conn.close();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}	      
		
	}	
	
}
