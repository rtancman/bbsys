package DAO.Campeonato.Julgamento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Control.Campeonato.Julgamento.Onda_control;
import DAO.BDConexao_dao;
import Model.Campeonato.Julgamento.Onda_model;
import Model.Usuario.Usuario_model;

public class Onda_dao {

	/**
	 * Funcao para cadastro de ondas que um atleta surfou
	 * @param Onda_model ondaModel
	 * @return Onda_model ondaModel
	 * */
	public Onda_model cadastrarOnda (Onda_model ondaModel){
		
	   //declarocoes de variaveis	
	   int res = 0;			
		
	   Connection conn = null;
	   Statement stmt = null;
	   
	   try{
		   
		   conn = BDConexao_dao.conectar();
		   
		   conn.setAutoCommit(false);
		   
	       // monto os valores para a minha query
		   String sql = "INSERT INTO `bodyboardsys`.`ondas` " +
	   		"(`idondas`, " +
	   		"`idatletadisputa`, " +
	   		"`ordem`, " +
	   		"`dataCadastro`) " +
	   		"values " +
	   		"(NULL, " +
	   		"'"+ondaModel.getAtletaDisputaModel().getIdatletadisputa()+"', " +
	   		"'"+ondaModel.getOrdem()+"', " +
	   		"NULL);";
		   
		   stmt = conn.createStatement();
	       			
	       stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
	       ResultSet rs = stmt.getGeneratedKeys();
	       
	       if (rs.next()) {
	    	   
	           res = rs.getInt(1); 

	           //seto o id da onda
	           ondaModel.setIdonda(res);
		    	 
	           //dou commit no BD das alteracoes
			   conn.commit();
			   
	       }else{
	    	   
	    	   res = 0;
	    	   ondaModel = null;
	    	   conn.rollback();
	    	   
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
			   		
			//fecho a conexao do BD
			conn.close();
			
			return ondaModel;
						
	   } catch (Exception e) {
			return null;
	   }	      
			
	}
	
	/**
     * Funcao que carrega a ultima onda surfada por um atleta
     * @param	Onda_model ondaModel
     * @param	 String tipoOrdem
     * @return	Onda_model ondaModel
     *  */
    public Onda_model carregarUltimaOnda( Onda_model ondaModel, String tipoOrdem ){
    	    	
    	//declarocoes de variaveis	
 	    Connection conn = null;
 	    Statement stmt = null;
 	    ResultSet res = null;
	   
		try{
	    		
	    	conn = BDConexao_dao.conectar();
	    	
	    	//Filtro da minha query
	    	String filtro = ""; 
	    	String ordem = ""; 
	    	
	    	if( tipoOrdem == "ordem" ){
				ordem += BDConexao_dao.adicionaOrdem(ordem,tipoOrdem,"DESC");
			}
	    	
	    	if( ondaModel.getIdonda() != 0 ){
	    		String valor = " idondas=";
	    		valor += Integer.toString(ondaModel.getIdonda());
	    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
	    	}
	    	
	    	if( ondaModel.getAtletaDisputaModel() != null ){
	    		
	    		if( ondaModel.getAtletaDisputaModel().getIdatletadisputa() != 0 ){
	    			String valor = " idatletadisputa=";
	    			valor += Integer.toString( ondaModel.getAtletaDisputaModel().getIdatletadisputa() );
	    			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
	    		}
	    	}
	    	
	    	//preparando a query
	    	Statement query = null;
	    	String sql = "SELECT " +
	    			 "*" +
	    			" FROM " +
	    			 "`bodyboardsys`.`ondas` " +
	    			"" + filtro + " " +
	    			"" + ordem + " " +
	    			"LIMIT 1;";
		
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
			
			ondaModel = Onda_control.carregarOndaResultSet(res);

			//fecho a conexao com o bd
			conn.close();
			
			return ondaModel;
			
		} catch (Exception e) {
			
			return null;
			
		}    	
    	
    	
    }
    
    /**
     * Funcao que edita uma notaFinal de uma onda apartir de uma instancia
     * @param	Onda_model ondaModel
     * @return	boolean
     *  */
    public static boolean editarNotaFinal(Onda_model ondaModel){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();
    	Statement stmt = null;
    	//ResultSet res = null;
    	int res = 0;
    	
    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( ondaModel.getIdonda() != 0 ){
    		String valor = " idondas=";
    		valor += Integer.toString(ondaModel.getIdonda()); 
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//Formato a data
		DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   
	       String sql = "UPDATE `bodyboardsys`.`ondas` " +
	       	"SET " +
		    "notafinal = '"+ondaModel.getNotaParcial()+"' " +
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
