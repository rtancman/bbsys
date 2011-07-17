/**
 * 
 */
package DAO.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Control.Usuario.Atleta_control;
import Control.Usuario.Gerente_control;
import Control.Usuario.Juiz_control;
import Control.Usuario.Usuario_control;
import DAO.BDConexao_dao;
import Model.Usuario.Usuario_model;

/**
 * @author Raffael
 *
 */
public class Usuario_dao {

	/**
	 * Construtor da classe
	 */
	public Usuario_dao() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Funcao para cadastrar um usuario
	 * @param Usuario_model usuario
	 * @return boolean
	 * */
	public boolean cadastrar(Usuario_model usuario){

	   Connection conn = null;
	   Statement stmt = null;
	   int res = 0;
	   
	   try{
		   conn = BDConexao_dao.conectar();
		   conn.setAutoCommit(false);
		   DateFormat dataFormatada = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	       String sql = "INSERT INTO `bodyboardsys`.`usuario`" +
		    "(nome," +
		    "sexo, " +
		    "telefone, " +
		    "email, " +
		    "senha, " +
		    "dataNascimento, " +
		    "idtipousuario, " +
		    "idcidadeu) " +
		   "VALUES" +
		   " ('"+usuario.getNome()+"', " +
		   	"'"+usuario.getSexo()+"', " +
		   	""+usuario.getTelefone()+", " +
		   	"'"+usuario.getEmail()+"', " +
		   	"'"+usuario.getSenha()+"', " +
		   	"'"+dataFormatada.format(usuario.getDataNascimento())+"', " +
		   	""+usuario.getTipoUsuario()+", " +
		   	"'"+usuario.getCidade()+"')";
	       
	       stmt = conn.createStatement();
	       stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
	       ResultSet rs = stmt.getGeneratedKeys();
	      
	       if (rs.next()) {
	    	   
	           res = rs.getInt(1);
	          
	           conn.commit();
	           
	           if( usuario.getTipoUsuario() == 2 ){
	        	   
	        	 //cadastro de atleta
    			 AtletaController atletaControl = new AtletaController();
    			 atletaControl.cadastrar(res);
    			 
    		   }else if( usuario.getTipoUsuario() == 3 ){
    			   
    			 //cadastro de juiz
      			 JuizController juizControl = new JuizController();
      			 juizControl.cadastrar(res);    
      			 
		       }else if( usuario.getTipoUsuario() == 1 ){
		    	   
		    	   //cadastro de gerente
		    	   GerenteController gerenteControl = new GerenteController();
		    	   gerenteControl.cadastrar(res); 
		    	   
		       }
    	       
	       }else{
	    	   res = 0;
	    	   conn.rollback();
	       }
		  
	       
        } catch(SQLException e){
	    	try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			System.out.println("Erro ao conectar com o banco: " + e.getMessage());
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
		}
        
	    if(res == 0){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	public Usuario_model efetuarLogin(String email,String senha) throws Exception{

	   Connection conn = null;	
	   conn = BDConexao_dao.conectar();
	    
	   Statement query = null;
       String sql = "SELECT * FROM `bodyboardsys`.`usuario` WHERE email = '"+email+"' and senha = '"+senha+"';";
       ResultSet res = null;
       try{
           query = conn.createStatement();
           res = query.executeQuery(sql);
        } catch(SQLException e){
            System.out.println("Erro ao conectar com o banco: " + e.getMessage());
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
		} 
        try{
        	
			Usuario_model usuarioModel = null;
			
			usuarioModel = UsuarioController.carregarResultSet(res);
	        
	        conn.close();
	        return usuarioModel;
        } catch (Exception e) {
			throw e;
		}
        
	}
	
	public Usuario_model enviaLogin(String email)throws Exception{

		//BDConexao_dao conn = new BDConexao_dao();
		//ResultSet rs = null;
		Connection conn = null;	
		conn = BDConexao_dao.conectar();
		ResultSet res = null;    
		Statement query = null;
		try{
			String sql = "SELECT * FROM `bodyboardsys`.`usuario` WHERE email = '"+email+"';";
			query = conn.createStatement();
			res = query.executeQuery(sql);
			//ResultSet res = conn.getInstance().executeQuery(sql);
			//ResultSetMetaData metaData = res.getMetaData();
		} catch(SQLException e){
			
			System.out.println("Erro ao conectar com o banco: " + e.getMessage());
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
			
		}
		
		try{
			
			Usuario_model usuarioModel = null;
			
			usuarioModel = UsuarioController.carregarResultSet(res);
	        
			conn.close();
        	return usuarioModel;
		}catch(Exception e){
			throw e;
			//System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		}
        	
	}

	public static ArrayList<Usuario_model> listar( Usuario_model usuarioModel, String tipo ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Usuario_model> listaUsuario = null;
		
		//Filtro da minha query
    	String filtro = ""; 
    	String ordem = ""; 
    	
		try{
			
			if(tipo != null){
				ordem += BDConexao_dao.adicionaOrdem(ordem,tipo,"ASC");
			}
			
	    	if( usuarioModel.getId() != 0 ){

	    			String valor = " idusuario=";
		    		valor += Integer.toString(usuarioModel.getId());
	    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    	
	    	if( usuarioModel.getNome() != null && usuarioModel.getNome() != "" ){
	    		
	    			String valor = " nome like'"+usuarioModel.getNome()+"%'";
		    		filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    		    	
	    	if( usuarioModel.getEmail() != null && usuarioModel.getEmail() != ""){

	    			String valor = " email like'"+usuarioModel.getEmail()+"%'";
	    			valor += usuarioModel.getEmail();
		    		filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    	
	    	if( usuarioModel.getTipoUsuario() != 0 ){

	    			String valor = " idtipousuario=";
	    			valor += Integer.toString(usuarioModel.getTipoUsuario());
	    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    		
	    	}
	    	
			conn = BDConexao_dao.conectar();

			String sql = "SELECT * FROM `bodyboardsys`.`usuario`" + filtro + ordem + ";";
	
			pstmt = conn.prepareStatement(sql);	

			res = pstmt.executeQuery();
			
			listaUsuario = UsuarioController.carregarListaResultSet(res);
			
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
				return listaUsuario;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
		
	}
	
    /**
     * Funcao que monta um menu atraves do tipo de cada usuario do sistema
     * @param	int usuarioTipo
     * @return	ArrayList<String>
     *  */
    public static ArrayList montaMenu(int usuarioTipo){
    	
       Connection conn = null;	
 	   conn = BDConexao_dao.conectar();
 	    
 	   Statement query = null;
       String sql = "select idfuncaof from `bodyboardsys`.`usuariofuncao` where idtipousuariof = 1";
       ResultSet res = null;
       
       try{
            query = (Statement) conn.createStatement();
            res = query.executeQuery(sql);
       } catch(SQLException e){
             System.out.println("Erro ao conectar com o banco: " + e.getMessage());
             System.err.println("SQLException: " + e.getMessage());
             System.err.println("SQLState: " + e.getSQLState());
             System.err.println("VendorError: " + e.getErrorCode());
       } 
       
       try{
		 	
		    while(res.next()){

			}
		    conn.close();

		 } catch (Exception e) {
			
		}    	
    	
    	return null;
    }

    /**
     * Funcao que carrega um Usuario_model apartir da instancia atual
     * @param	Usuario_model usuarioModel
     * @return	Usuario_model usuarioModel
     *  */
    public Usuario_model carregar( Usuario_model usuarioModel){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();

    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( usuarioModel.getId() != 0 ){
    		String valor = " idusuario=";
    		valor += Integer.toString(usuarioModel.getId()); 
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
 
    	if( usuarioModel.getEmail() != null && usuarioModel.getEmail() != ""){
    		String valor = " email='"+usuarioModel.getEmail()+"'";
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT * FROM `bodyboardsys`.`usuario` " + filtro;
    	
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
    		usuarioModel = UsuarioController.carregarResultSet(res);
    		conn.close();
    		return usuarioModel;
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    }
    
    /**
     * Funcao que edita um Usuario_model apartir de uma instancia
     * @param	Usuario_model usuarioModel
     * @return	Usuario_model usuarioModel
     *  */
    public Usuario_model editar( Usuario_model usuarioModel){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();
    	Statement stmt = null;
    	//ResultSet res = null;
    	int res = 0;
    	
    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( usuarioModel.getId() != 0 ){
    		String valor = " idusuario=";
    		valor += Integer.toString(usuarioModel.getId()); 
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//Formato a data
		DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   
	       String sql = "UPDATE `bodyboardsys`.`usuario` " +
	       	"SET " +
		    "nome = '"+usuarioModel.getNome()+"'," +
		    "sexo = '"+usuarioModel.getSexo()+"', " +
		    "telefone = "+usuarioModel.getTelefone()+", " +
		    "email = '"+usuarioModel.getEmail()+"', " +
		    "senha = '"+usuarioModel.getSenha()+"', " +
		    "dataNascimento = '"+dataFormataData.format( usuarioModel.getDataNascimento() )+"', " +
		    "idtipousuario = "+usuarioModel.getTipoUsuario()+", " +
		    "idcidadeu = '"+usuarioModel.getCidade()+"' " +
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
			} catch (SQLException e2) {
	    		System.out.println("Erro ao conectar com o banco: " + e.getMessage());
	    		System.err.println("SQLException: " + e.getMessage());
	    		System.err.println("SQLState: " + e.getSQLState());
	    		System.err.println("VendorError: " + e.getErrorCode());	    		
	    		return null;
			}
    		    		
    		return null;
    		
    	} 
    	
    	try{
    		//dou commit no BD das alteracoes do usuario
    		conn.commit();
    		    		
    		//fecho a conexao do BD
    		conn.close();
    		
    		return usuarioModel;
    	
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    }

}
