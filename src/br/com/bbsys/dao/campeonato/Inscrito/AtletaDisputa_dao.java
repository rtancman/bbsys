package DAO.Campeonato.Inscrito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Control.Campeonato.Inscrito.AtletaDisputa_control;
import DAO.BDConexao_dao;
import Model.Campeonato.Inscrito.AtletaDisputa_model;
import Model.Campeonato.Julgamento.Onda_model;

public class AtletaDisputa_dao {
	
	/**
     * Funcao para persistir um atletaDisputa_model
     * @param	AtletaDisputa_model atletaDisputaModel
     * @return	boolean
     *  */
	public boolean salvarAtletaDisputa( AtletaDisputa_model atletaDisputaModel ){
		
		//Inicio as variaveis que serão utilizados para este metodo
	    boolean resultado = false;
	 	
	   Connection conn = null;
	   Statement stmt = null;
	   
	   try{
		   
		   conn = BDConexao_dao.conectar();
		   
		   conn.setAutoCommit(false);
		   
	       // monto os valores para a minha query
		   String sql = "INSERT INTO `bodyboardsys`.`atletadisputa` " +
			"(`idatletadisputa`, " +
			"`idbateria`, " +
			"`camisetaCor`, " +
			"`dataCadastro`, " +
			"`idatleta` " +
			") " +
			"VALUES " + 
			"(NULL, " +
   			"'"+atletaDisputaModel.getBateriaModel().getIdbateria()+"', " +
   			"'"+atletaDisputaModel.getCamisetacor()+"', " +
   			"NULL, " +
   			"'"+atletaDisputaModel.getAtletaModel().getidatleta()+"');";
   			
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
			//dou commit no BD das alteracoes do usuario
			conn.commit();
			    		
			//fecho a conexao do BD
			conn.close();
			
			return true;
		
	   } catch (Exception e) {
			return false;
	   }
	}
	
	/**
	 * Funcao para deletar um atletaDisputa_model
	 * @param	AtletaDisputa_model atletaDisputaModel
	 * @return	boolean
	 *  */
	public Boolean deletarAtletaDisputa( AtletaDisputa_model atletaDisputaModel ){
		
		//Inicio as variaveis que serão utilizados para este metodo
		boolean resultado = false;
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			conn.setAutoCommit(false);
			
			// monto os valores para a minha query
			String sql = "delete from `bodyboardsys`.`atletadisputa` where `idatletadisputa`='"+atletaDisputaModel.getIdatletadisputa()+"'";
			
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
			//dou commit no BD das alteracoes do usuario
			conn.commit();
			
			//fecho a conexao do BD
			conn.close();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Funcao para deletar um atletaDisputa_model
	 * @param	AtletaDisputa_model atletaDisputaModel
	 * @return	boolean
	 *  */
	public Boolean deletarTodosAtletaDisputa( AtletaDisputa_model atletaDisputaModel ){
		
		//Inicio as variaveis que serão utilizados para este metodo
		boolean resultado = false;
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			conn.setAutoCommit(false);
			
			// monto os valores para a minha query
			String sql = "delete from `bodyboardsys`.`atletadisputa` where `idbateria`='"+atletaDisputaModel.getBateriaModel().getIdbateria()+"'";
			
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
			//dou commit no BD das alteracoes do usuario
			conn.commit();
			
			//fecho a conexao do BD
			conn.close();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	   
	/**
	 * Funcao para deletar um atletaDisputa_model
	 * @param	AtletaDisputa_model atletaDisputaModel
	 * @param	String tipoOrdem atletaDisputaModel
	 * @return	ArrayList<AtletaDisputa_model>
	 *  */
	public ArrayList<AtletaDisputa_model> listarAtletaDisputa( AtletaDisputa_model atletaDisputaModel, String tipoOrdem ){
		
		//Inicio as variaveis que serão utilizados para este metodo
		ArrayList<AtletaDisputa_model> listaAtletaDisputaModel = null;
		AtletaDisputa_control atletaDisputaControl = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		//Filtro da minha query
    	String filtro = "";
    	String join = "";
    	String ordem = ""; 
		
    	try{
			
			conn = BDConexao_dao.conectar();
			
			if(tipoOrdem != null){
				ordem += BDConexao_dao.adicionaOrdem(ordem,tipoOrdem,"ASC");
			}
			
	    	if( atletaDisputaModel.getIdatletadisputa() != 0 ){
	    		 
	    			String valor = " idatletadisputa=";
		    		valor += Integer.toString(atletaDisputaModel.getIdatletadisputa());
	    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    	}
	    	
	    	if( atletaDisputaModel.getCamisetacor() != null && atletaDisputaModel.getCamisetacor() != "" ){
	    		
	    		String valor = " camisetaCor=";
	    		valor += atletaDisputaModel.getCamisetacor();
	    		filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    	
	    	if( atletaDisputaModel.getBateriaModel() != null ){
	    		
	    		if( atletaDisputaModel.getBateriaModel().getIdbateria() != 0 ){
	    			
	    			String valor = " ad.idbateria=";
		    		valor += Integer.toString(atletaDisputaModel.getBateriaModel().getIdbateria());
	    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    			
	    		}
	    		
	    	}
	    	
	    	if( atletaDisputaModel.getAtletaModel() != null ){
	    		
	    		if( atletaDisputaModel.getAtletaModel().getidatleta() != 0 ){
	    			
	    			String valor = " ad.idatleta=";
		    		valor += Integer.toString(atletaDisputaModel.getAtletaModel().getidatleta());
	    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    			
	    		}
	    		
	    	}
	    	

			String sql = "SELECT ad.*, u.* FROM `bodyboardsys`.`atletadisputa` ad " +
					"JOIN `bodyboardsys`.`atleta` a ON ( a.idatleta = ad.idatleta) " +
					"JOIN `bodyboardsys`.`usuario` u ON ( u.idusuario = a.idusuarioa) " +
					""+filtro+" "+ordem+";";
	
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
			atletaDisputaControl = new AtletaDisputa_control();
    		listaAtletaDisputaModel = atletaDisputaControl.carregarListaAtletaDisputa(res);
			
			//fecho a conexao do BD
			conn.close();
			
			return listaAtletaDisputaModel;
			
		} catch (Exception e) {
			return null;
		}
	}
	
   /**
    * Funcao que edita a nota do atleta na bateria
    * @param	AtletaDisputa_model atletaDisputaModel
    * @return	boolean
    * */
   public static boolean editarNotaAtletaDisputa(AtletaDisputa_model atletaDisputaModel){
   	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();
    	Statement stmt = null;
    	//ResultSet res = null;
    	int res = 0;
    	
    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( atletaDisputaModel.getIdatletadisputa() != 0 ){
    		String valor = " idatletadisputa=";
    		valor += Integer.toString(atletaDisputaModel.getIdatletadisputa()); 
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	   
	       String sql = "UPDATE `bodyboardsys`.`atletadisputa` " +
	       	"SET " +
		    "nota = '"+atletaDisputaModel.getNotaFinal()+"' " +
		    filtro;
    	
    	try{
    		
    		conn.setAutoCommit(false);
    		
    		stmt = conn.createStatement();
			
			res = stmt.executeUpdate(sql);
    		
    	} catch(SQLException e){
    		
    		System.out.println("Erro ao conectar com o banco: " + e.getMessage());
    		System.err.println("SQLException: " + e.getMessage());
    		System.err.println("SQLState: " + e.getSQLState());
    		System.err.println("VendorError: " + e.getErrorCode());
    		
    		try {
    			// dou um rollback no BD caso ocorra alguma excessao ao atualizar o usuario
    			conn.rollback();
        		
        		//fecho a conexao do BD
        		conn.close();
        		
			} catch (SQLException e2) {
	    		System.out.println("Erro ao conectar com o banco: " + e.getMessage());
	    		System.err.println("SQLException: " + e.getMessage());
	    		System.err.println("SQLState: " + e.getSQLState());
	    		System.err.println("VendorError: " + e.getErrorCode());	    		
	    		return false;
			}
    		    		
    		return false;
    		
    	} 
    	
    	try{
    		//dou commit no BD das alteracoes do usuario
    		conn.commit();
    		    		
    		//fecho a conexao do BD
    		conn.close();
    		
    		return true;
    	
    	} catch (Exception e) {
    		return false;
    	}    	
    	
    }	
	
}
