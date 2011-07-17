package DAO.Campeonato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Control.Campeonato.Campeonato_control;
import DAO.BDConexao_dao;
import Model.Campeonato.Campeonato_model;

public class Campeonato_dao {
	
	/**
	 * Funcao para cadastro de campeonato
	 * @param Campeonato_model campeonatoModel
	 * @return boolean
	 * */
	public boolean cadastrarCampeonato (Campeonato_model campeonatoModel){
		
	   Connection conn = null;
	   Statement query = null;

	   int res = 0;			
	
	   try{
		   conn = BDConexao_dao.conectar();
		   
		   conn.setAutoCommit(false);
		   
		   //Formato a data
		   DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   
		   String sql = "INSERT INTO `bodyboardsys`.`campeonato` " +
		   		"(`idcampeonato`, " +
		   		"`nome`, " +
		   		"`dataInicio`, " +
		   		"`dataEncerramento`, " +
		   		"`dataCadastro`) " +
		   		"values " +
		   		"(null, " +
		   		"'"+campeonatoModel.getNome()+"', " +
		   		"'"+dataFormataData.format(campeonatoModel.getDataInicio())+"', " +
		   		"'"+dataFormataData.format(campeonatoModel.getDataEncerramento())+"', " +
		   		"NULL)";
		   
	       query = conn.createStatement();
	       
	       query.executeUpdate(sql);
	       
	       //implementacao para pegar o ultimo id gerado
	       PreparedStatement ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");

	       ResultSet rs = ps.executeQuery();

	       if (rs.next()) {
	           res = rs.getInt(1);
	       }
	       //ResultSet chaveGerada = query.getGeneratedKeys(); 
	       
	       //ResultSet rs = query.execute("SELECT LAST_INSERT_ID()");
/*
	       int id = 0;
	       if (rs.next()) {
	    	   campeonatoModel.setIdcampeonato(rs.getInt(1));
	       }*/
	       conn.commit();
	    
	   } catch(SQLException e){
	        System.out.println("Erro ao conectar com o banco: " + e.getMessage());
	        System.err.println("SQLException: " + e.getMessage());
	        System.err.println("SQLState: " + e.getSQLState());
	        System.err.println("VendorError: " + e.getErrorCode());
		}
	   finally{
			try {
				query.close();
				conn.close();
				conn = null;				
				query = null;					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	   
	    if(res == 0){
	    	try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return false;
	    }else{
	    	campeonatoModel.setIdcampeonato(res);
	    	return true;
	    }
			
	}
		
	public static ArrayList<Campeonato_model> relatorioCampeonato(){
			
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Campeonato_model> listaCampeonato = new ArrayList<Campeonato_model>();
		
		try{
			
			conn = BDConexao_dao.conectar();

			String sql = "SELECT * FROM `bodyboardsys`.`campeonato`;";
	
			pstmt = conn.prepareStatement(sql);	

			res = pstmt.executeQuery();
			
			listaCampeonato = Campeonato_control.carregarListaResultSet(res);
			
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
				return listaCampeonato;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
		
	}
	
	public static ArrayList<Campeonato_model> listar(Campeonato_model campeonatoModel, String ordenar){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Campeonato_model> listaCampeonato = new ArrayList<Campeonato_model>();
		
		//Filtro da minha query
    	String filtro = ""; 
    	String ordem = ""; 
    	
		try{
			
			if(ordenar != null){
				ordem += BDConexao_dao.adicionaOrdem(ordem,ordenar,"ASC");
			}
			
	    	if( campeonatoModel.getIdcampeonato() != 0 ){
	    		 
    			String valor = " idcampeonato=";
	    		valor += Integer.toString(campeonatoModel.getIdcampeonato());
    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    	
	    	if( campeonatoModel.getNome() != null && campeonatoModel.getNome() != "" ){
	    		
    			String valor = " nome like'"+campeonatoModel.getNome()+"%'";
	    		filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    		    	
	    	if( campeonatoModel.getStatus() != 0 ){
	    		
	    		String valor = " status=";
	    		valor += Integer.toString(campeonatoModel.getStatus());
    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    	
			conn = BDConexao_dao.conectar();
			
			String sql = "SELECT * FROM `bodyboardsys`.`campeonato` " + filtro+ " " + ordem + ";";
			
			pstmt = conn.prepareStatement(sql);	
			
			res = pstmt.executeQuery();
			
			listaCampeonato = Campeonato_control.carregarListaResultSet(res);
			
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
				return listaCampeonato;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
		
	}

	/**
     * Funcao que carrega um Campeonato_model apartir da instancia atual
     * @param	Campeonato_model campeonatoModel
     * @return	Campeonato_model campeonatoModel
     * */
	public Campeonato_model carregar( Campeonato_model campeonatoModel ){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();

    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( campeonatoModel.getIdcampeonato() != 0 ){
    		String valor = " idcampeonato=";
    		valor += Integer.toString(campeonatoModel.getIdcampeonato());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT * FROM `bodyboardsys`.`campeonato` " + filtro;
    	
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
    		
    		campeonatoModel = Campeonato_control.carregarResultSet(res);
    		conn.close();
    		return campeonatoModel;
    		
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    }

	public boolean editar( Campeonato_model campeonatoModel ){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();
    	Statement stmt = null;
    	
    	DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	
    	int res = 0;
    	
    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( campeonatoModel.getIdcampeonato() != 0 ){
    		String valor = " idcampeonato=";
    		valor += Integer.toString(campeonatoModel.getIdcampeonato());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
	       String sql = "UPDATE `bodyboardsys`.`campeonato` " +
	       	"SET " +
		    "nome = '"+campeonatoModel.getNome()+"'," +
		    "dataInicio = '"+dataFormataData.format(campeonatoModel.getDataInicio())+"'," +
		    "dataEncerramento = '"+dataFormataData.format(campeonatoModel.getDataEncerramento())+"' " +
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
	 * Funcao que carrega um campeonato inteiro com todas as etapas e baterias 
	 * @param Campeonato_model
	 * return Campeonato_model
	 * */
	
	public Campeonato_model carregarCampeonatoInteiro( Campeonato_model campeonatoModel ){
		
		Connection conn = null;	
		conn = BDConexao_dao.conectar();
		
		//Filtro da minha query
		String filtro = ""; 
		
		if( campeonatoModel.getIdcampeonato() != 0 ){
			String valor = " ce.idcampeonato=";
			valor += Integer.toString(campeonatoModel.getIdcampeonato());
			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
		}
		
		//preparando a query
		Statement query = null;
		String sql = "SELECT " +
				 "* " +
				"FROM " +
				 "`bodyboardsys`.`campeonato` c" +
				"INNER JOIN " +
				 "`bodyboardsys`.`campeonatoetapa` ce ON ce.idcampeonato = c.idcampeonato " +
				"INNER JOIN " +
				 "`bodyboardsys`.`bateria` b ON b.idcampeonatoetapa = ce.idcampeonatoetapa " +
				"" + filtro;
		
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
			
			campeonatoModel = Campeonato_control.carregarCampeonatoInteiroResultSet(res);
			conn.close();
			return campeonatoModel;
			
		} catch (Exception e) {
			return null;
		}    	
		
	}
}
