package DAO.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.Usuario.Gerente_control;
import DAO.BDConexao_dao;
import Model.Usuario.Gerente_model;

public class Gerente_dao {
	
	/**
	 * @category Fun��o para listar os gerentes cadastrados no sistema
	 * @author Raffael
	 * @param String nomeBusca - lista por nome buscado
	 * @param String campoOrdem - ordena pelo campo passado
	 * @return ArrayList<Gerente_model>
	 * */
	public static ArrayList<Gerente_model> listar(String nomeBusca, String campoOrdem){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Gerente_model> listaGerente = new ArrayList<Gerente_model>();
		String filtro = "";
		
		//filtros para listar um atleta
    	if(nomeBusca != null){
    		filtro += " WHERE u.nome like '%"+nomeBusca+"%'";
    	}
    	if(campoOrdem != null){
    		
    		if( campoOrdem.equals("idgerente") ){
    			filtro += " ORDER BY g."+campoOrdem;
    		}else{
    			filtro += " ORDER BY u."+campoOrdem;
    		}
    		
    	}
		
		try{
			
			conn = BDConexao_dao.conectar();

			String sql = "SELECT u.*,g.* " +
			" FROM " +
			" `bodyboardsys`.`usuario` u " +
			"INNER JOIN `bodyboardsys`.`gerente` g on g.idusuario = u.idusuario";
			
			//se tenho um filtro adiciono na query
			if(filtro != ""){
				sql = sql + filtro;
			}
			
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

		   listaGerente = GerenteController.carregarListaUsuarioGerenteResultSet(res);
    		
			//fecho a conexao do BD
			conn.close();
			
    		return listaGerente;
		
	   } catch (Exception e) {
			return null;
	   }
		
	}
	
	/**
	 * @category Fun��o para cadastrar um gerente
	 * @author Raffael
	 * @param int usuarioID - id do usuario
	 * @return boolean
	 * */
	public boolean cadastrar( int usuarioID ){
		
		Connection conn = null;
		Statement stmt = null;
		boolean rs = false;	
		int res = 0;
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			//desabilito o autocommit
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO `bodyboardsys`.`gerente` " +
				 			 "(`idgerente`, " +
				 			 "`idusuario`, " +
				 			 "`dataCadastro`) " +
						"VALUES " +
							"(NULL, " +
							""+usuarioID+", " +
							"NULL);";
				
			stmt = conn.createStatement();
			
			res = stmt.executeUpdate(sql);
			
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
     * Funcao que carrega um Gerente_model completo apartir da instancia atual
     * @param	Gerente_model gerenteModel
     * @return	Gerente_model gerenteModel
     *  */
    public Gerente_model carregarUsuarioGerente( Gerente_model gerenteModel ){
    	    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();

    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( gerenteModel.getId() != 0 ){
    		String valor = " u.idusuario=";
    		valor += Integer.toString(gerenteModel.getId());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	if( gerenteModel.getIdgerente() != 0 ){
    		String valor = " g.idgerente=";
    		valor += Integer.toString(gerenteModel.getIdgerente());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	if( gerenteModel.getEmail() != null && gerenteModel.getEmail() != ""){
    		String valor = " u.email=" + gerenteModel.getEmail();
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT " +
    			 "u.*," +
    			 "g.*" +
    			" FROM " +
    			 "`bodyboardsys`.`usuario` u " +
    			"INNER JOIN " +
    			 "`bodyboardsys`.`gerente` g ON g.idusuario = u.idusuario" +
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
    		gerenteModel = GerenteController.carregarUsuarioGerenteResultSet(res);
    		conn.close();
    		return gerenteModel;
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    }
    
    /**
	 * @category Fun��o para remover um gerente
	 * @author Raffael
	 * @param int idUsuario
	 * @return boolean
	 * */
	public static boolean removerGerente( int idUsaurio ){
		
		Connection conn = null;
		Statement stmt = null;
		int res = 0;
		boolean rs = false;			
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			//desabilito o autocommit
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM `bodyboardsys`.`gerente` " +
			"where " +
			"`idusuarioj`="+idUsaurio+";";
			
			stmt = conn.createStatement();
			
			res = stmt.executeUpdate(sql);
			
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
				    		
				if(res != 0){
					rs = true;
					conn.commit();
				}else{
					rs = false;
					conn.rollback();
				}
				
				//fecho a conexao do BD
				conn.close();
				
				return rs;
				
		   } catch (Exception e) {
				return false;
		   }
	} 
	
}
