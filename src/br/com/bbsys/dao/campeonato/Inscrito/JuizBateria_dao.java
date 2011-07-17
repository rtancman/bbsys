package DAO.Campeonato.Inscrito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Control.Campeonato.Inscrito.JuizBateria_control;
import DAO.BDConexao_dao;
import Model.Campeonato.Inscrito.AtletaDisputa_model;
import Model.Campeonato.Inscrito.JuizBateria_model;
import Model.Usuario.Juiz_model;

public class JuizBateria_dao {
	
	/**
	 * Funcao para carregar um obj JuizBateria_model
	 * @param JuizBateria_model juizBateriaModel
	 * @return JuizBateria_model juizBateriaModel
	 * */
	public JuizBateria_model carregar(JuizBateria_model juizBateriaModel){
		
		//Inicio as variaveis que serão utilizados para este metodo
		JuizBateria_model juizBateriaModelAux = null;
		JuizBateria_control juizBateriaControl = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		//Filtro da minha query
    	String filtro = "";
    	String join = "";
		
    	try{
			
			conn = BDConexao_dao.conectar();

	    	if( juizBateriaModel.getIdinscricaojuiz() != 0 ){
    			
	    		String valor = " jb.idinscricaojuiz=";
	    		valor += Integer.toString(juizBateriaModel.getIdjuizbateria());
    			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
    			
	    	}
	    	
	    	if( juizBateriaModel.getIdjuizbateria() != 0 ){
	    		
    			String valor = " jb.idjuizbateria=";
	    		valor += Integer.toString(juizBateriaModel.getIdjuizbateria());
    			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
    			
	    	}
	    	//trocar pelo obj bateriaModel
	    	if( juizBateriaModel.getBateriaModel() != null ){
	    		
	    		if( juizBateriaModel.getBateriaModel().getIdbateria() != 0 ){
	    			
	    			String valor = " b.idbateria=";
		    		valor += Integer.toString(juizBateriaModel.getBateriaModel().getIdbateria());
	    			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    			
	    		}
	    		
	    	}
	    	
	    	if( juizBateriaModel.getJuizModel() != null ){
	    		
	    		if( juizBateriaModel.getJuizModel().getId() != 0 ){
	    			
	    			String valor = " u.idusuario=";
	    			valor += Integer.toString(juizBateriaModel.getJuizModel().getId());
	    			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    			
	    		}
	    		
	    		if( juizBateriaModel.getJuizModel().getIdjuiz() != 0 ){
	    			
	    			String valor = " j.idjuiz=";
	    			valor += Integer.toString(juizBateriaModel.getJuizModel().getIdjuiz());
	    			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    			
	    		}
	    		
	    	}
	    	

			String sql = "SELECT" +
					" j.*," +
					" u.*," +
					" b.*," +
					" jb.* " +
					"FROM " +
					"`bodyboardsys`.`juizbateria` jb " +
					"JOIN `bodyboardsys`.`inscricaojuiz` ij ON ( ij.idinscricaojuiz = jb.idinscricaojuiz ) " +
					"JOIN `bodyboardsys`.`juiz` j ON ( j.idjuiz = ij.idjuiz ) " +
					"JOIN `bodyboardsys`.`usuario` u ON ( u.idusuario = j.idusuarioj ) " +
					"JOIN `bodyboardsys`.`bateria` b ON ( b.idbateria = jb.idbateria ) " +
					""+filtro+";";
	
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
			juizBateriaControl = new JuizBateria_control();
			juizBateriaModelAux = juizBateriaControl.carregarJuizBateria(res);
			
			//fecho a conexao do BD
			conn.close();
			
			return juizBateriaModelAux;
			
		} catch (Exception e) {
			return null;
		}
				
	}
	
	/**
	 * Funcao para carregar um obj JuizBateria_model apartir dos juizes inscritos em um campeonato
	 * @param JuizBateria_model juizBateriaModel
	 * @return JuizBateria_model juizBateriaModel
	 * */
	public JuizBateria_model carregarJuizInscrito(JuizBateria_model juizBateriaModel){
		
		//Inicio as variaveis que serão utilizados para este metodo
		JuizBateria_model juizBateriaModelAux = null;
		JuizBateria_control juizBateriaControl = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		//Filtro da minha query
		String filtro = "";
		String join = "";
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			if( juizBateriaModel.getJuizModel() != null ){
	    		
	    		if( juizBateriaModel.getJuizModel().getId() != 0 ){
	    			
	    			String valor = " u.idusuario=";
	    			valor += Integer.toString(juizBateriaModel.getJuizModel().getId());
	    			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    			
	    		}
	    		
	    		if( juizBateriaModel.getJuizModel().getIdjuiz() != 0 ){
	    			
	    			String valor = " j.idjuiz=";
	    			valor += Integer.toString(juizBateriaModel.getJuizModel().getIdjuiz());
	    			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    			
	    		}
	    		
	    	}
						
			String sql = "SELECT" +
			" ij.*," +
			" j.*," +
			" u.*" +
			"FROM " +
			"`bodyboardsys`.`inscricaojuiz` ij " +
			"JOIN `bodyboardsys`.`juiz` j ON ( j.idjuiz = ij.idjuiz ) " +
			"JOIN `bodyboardsys`.`usuario` u ON ( u.idusuario = j.idusuarioj ) " +
			""+filtro+";";
			
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
			juizBateriaControl = new JuizBateria_control();
			juizBateriaModelAux = juizBateriaControl.carregarJuizInscritoResultSet(res);
			
			//fecho a conexao do BD
			conn.close();
			
			return juizBateriaModelAux;
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
     * Funcao para persistir um JuizBateria
     * @param	JuizBateria_model juizBateriaModel
     * @return	boolean
     *  */
	public boolean salvarJuizBateria( JuizBateria_model juizBateriaModel ){
		
		//Inicio as variaveis que serão utilizados para este metodo
	    boolean resultado = false;
	 	
	   Connection conn = null;
	   Statement stmt = null;
	   
	   try{
		   
		   conn = BDConexao_dao.conectar();
		   
		   conn.setAutoCommit(false);
		   
	       // monto os valores para a minha query
		   String sql = "INSERT INTO `bodyboardsys`.`juizbateria` " +
			"(`idjuizbateria`, " +
			"`idinscricaojuiz`, " +
			"`idbateria`, " +
			"`status`, " +
			"`dataCadastro` " +
			") " +
			"VALUES " + 
			"(NULL, " +
   			"'"+juizBateriaModel.getIdinscricaojuiz()+"', " +
   			"'"+juizBateriaModel.getBateriaModel().getIdbateria()+"', " +
   			"1, " +
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
