package DAO.Campeonato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Control.Campeonato.CampeonatoEtapa_control;
import Control.Campeonato.Campeonato_control;
import Control.Campeonato.Inscrito.JuizBateria_control;
import Control.Usuario.Atleta_control;
import Control.Usuario.Juiz_control;
import DAO.BDConexao_dao;
import Model.Campeonato.CampeonatoEtapa_model;
import Model.Campeonato.Campeonato_model;
import Model.Usuario.Atleta_model;
import Model.Usuario.Juiz_model;

public class CampeonatoEtapa_dao extends Campeonato_dao {
	
	/**
	 * Funcao para cadastro de campeonato etapa
	 * @param CampeonatoEtapa_model campeonatoEtapaModel
	 * @return boolean
	 * */
	public boolean cadastrarCampeonatoEtapa (CampeonatoEtapa_model campeonatoEtapaModel){
		
	   Connection conn = null;
	   Statement stmt = null;

	   try{
		   
		   conn = BDConexao_dao.conectar();
		   
		   conn.setAutoCommit(false);
		   
		   //Formato a data
		   DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   
		 //Cadastrando a etapa
		   String sql = "INSERT INTO `bodyboardsys`.`campeonatoetapa` " +
			"(`idcampeonatoetapa`, " +
			"`nome`, " +
			"`praia`, " +
			"`dataInicio`, " +
			"`dataEncerramento`, " +
			"`cidade`, " +
			"`idestado`, " +
			"`idpais`, " +
			"`idcampeonato`, " +
			"`dataCadastro`) " +
			"values " +
			"(NULL, " +
			"'"+campeonatoEtapaModel.getNomeEtapa()+"', " +
			"'"+campeonatoEtapaModel.getPraia()+"', " +
			"'"+dataFormataData.format(campeonatoEtapaModel.getDataInicioEtapa())+"', " +
			"'"+dataFormataData.format(campeonatoEtapaModel.getDataEncerramentoEtapa())+"', " +
			"'"+campeonatoEtapaModel.getCidade()+"', " +
			"'"+campeonatoEtapaModel.getIdestado()+"', " +
			"'"+campeonatoEtapaModel.getIdpais()+"', " +
			"'"+campeonatoEtapaModel.getIdcampeonato()+"', " +
			"NULL);";
		   
		    stmt = conn.createStatement();
	        stmt.execute(sql);				
	    					
	   } catch(SQLException e){
		   
		   try {
				// dou um rollback no BD caso ocorra alguma excessao ao atualizar o usuario
				conn.rollback();

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
	
	public Campeonato_model cadastrarCampeonatoInteiro (Campeonato_model campeonatoModel, CampeonatoEtapa_model campeonatoEtapaModel){
		
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   Statement stmt = null;
		   
		   try{
			   
			   conn = BDConexao_dao.conectar();
			   
			   conn.setAutoCommit(false);
			   
			   //Formato a data
			   DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			   
			   //Cadastrando o campeonato
			   String sql = "INSERT INTO `bodyboardsys`.`campeonato` " +
		   		"(`idcampeonato`, " +
		   		"`nome`, " +
		   		"`dataInicio`, " +
		   		"`dataEncerramento`, " +
		   		"`dataCadastro`) " +
		   		"values " +
		   		"(NULL, " +
		   		"'"+campeonatoModel.getNome()+"', " +
		   		"'"+dataFormataData.format(campeonatoModel.getDataInicio())+"', " +
		   		"'"+dataFormataData.format(campeonatoModel.getDataEncerramento())+"', " +
		   		"NULL)";
		   
			   stmt = conn.createStatement();
		       stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
		       ResultSet res = stmt.getGeneratedKeys();
		      
		       int campeonatoId = 0;
		       
		       if ( res.next() ) {
		    	   campeonatoId = res.getInt(1);   	    	       
		       }else{
		    	   conn.rollback();
		    	   return null;
		       }   
	            
			        
	            //seto o id deste campeonato
	            campeonatoModel.setIdcampeonato(campeonatoId);
	            
			   //Cadastrando a etapa deste campeonato
			   String sql2 = "INSERT INTO `bodyboardsys`.`campeonatoetapa` " +
				"(`idcampeonatoetapa`, " +
				"`nome`, " +
				"`praia`, " +
				"`dataInicio`, " +
				"`dataEncerramento`, " +
				"`cidade`, " +
				"`idestado`, " +
				"`idpais`, " +
				"`idcampeonato`, " +
				"`dataCadastro`) " +
				"values " +
				"(NULL, " +
				"'"+campeonatoEtapaModel.getNomeEtapa()+"', " +
				"'"+campeonatoEtapaModel.getPraia()+"', " +
				"'"+dataFormataData.format(campeonatoEtapaModel.getDataInicioEtapa())+"', " +
				"'"+dataFormataData.format(campeonatoEtapaModel.getDataEncerramentoEtapa())+"', " +
				"'"+campeonatoEtapaModel.getCidade()+"', " +
				"'"+campeonatoEtapaModel.getIdestado()+"', " +
				"'"+campeonatoEtapaModel.getIdpais()+"', " +
				"'"+campeonatoModel.getIdcampeonato()+"', " +
				"NULL);";
			   
			    stmt = conn.createStatement();
		        stmt.execute(sql2);				
		    
		   } catch(SQLException e){
			   
			   try {
					// dou um rollback no BD caso ocorra alguma excessao ao inserir o Campeonato
					conn.rollback();

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
				
				return campeonatoModel;
			
		   } catch (Exception e) {
				return null;
		   }
	}
	
	public static ArrayList<CampeonatoEtapa_model> listar(CampeonatoEtapa_model campeonatoEtapa,String tipoOrdem){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa = new ArrayList<CampeonatoEtapa_model>();
		
		//Filtro da minha query
    	String filtro = "";
    	String ordem = ""; 
    	
		try{
			
			conn = BDConexao_dao.conectar();

			if(tipoOrdem != null){
				ordem += BDConexao_dao.adicionaOrdem(ordem,tipoOrdem,"ASC");
			}
			
	    	if( campeonatoEtapa.getIdcampeonato() != 0 ){
	    		 
	    			String valor = " idcampeonato=";
		    		valor += Integer.toString(campeonatoEtapa.getIdcampeonato());
	    			filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    	}
	    	
	    	if( campeonatoEtapa.getIdcampeonatoetapa() != 0 ){
	    		
	    		String valor = " idcampeonatoetapa=";
	    		valor += Integer.toString(campeonatoEtapa.getIdcampeonatoetapa());
	    		filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");

	    	}
	    	
	    	if( campeonatoEtapa.getNomeEtapa() != null && campeonatoEtapa.getNomeEtapa() != "" ){
	    		
	    		String valor = " nome like'" + campeonatoEtapa.getNomeEtapa() + "%'";
	    		filtro += BDConexao_dao.adicionaFiltro(filtro, valor, "");
	    	}
	    	

			String sql = "SELECT * FROM `bodyboardsys`.`campeonatoetapa` "+filtro+" "+ordem+";";
	
			pstmt = conn.prepareStatement(sql);	

			res = pstmt.executeQuery();
			listaCampeonatoEtapa = CampeonatoEtapa_control.carregarListaEtapaResultSet(res);
			
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
				return listaCampeonatoEtapa;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
		
	}
	
	public CampeonatoEtapa_model carregarEtapa( CampeonatoEtapa_model campeonatoEtapaModel ){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();

    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( campeonatoEtapaModel.getIdcampeonatoetapa() != 0 ){
    		String valor = " idcampeonatoetapa=";
    		valor += Integer.toString(campeonatoEtapaModel.getIdcampeonatoetapa());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT * FROM `bodyboardsys`.`campeonatoetapa` " + filtro;
    	
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
    		
    		campeonatoEtapaModel = CampeonatoEtapa_control.carregarEtapaResultSet(res);
    		conn.close();
    		return campeonatoEtapaModel;
    		
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    }
	
	public CampeonatoEtapa_model carregarCampeonatoMaisEtapa( CampeonatoEtapa_model campeonatoEtapaModel ){
		
		Connection conn = null;	
		conn = BDConexao_dao.conectar();
		
		//Filtro da minha query
		String filtro = ""; 
		
		if( campeonatoEtapaModel.getIdcampeonatoetapa() != 0 ){
			String valor = " ce.idcampeonatoetapa=";
			valor += Integer.toString(campeonatoEtapaModel.getIdcampeonatoetapa());
			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
		}
		
		//preparando a query
		Statement query = null;
		String sql = "SELECT " +
				 "* " +
				"FROM " +
				 "`bodyboardsys`.`campeonatoetapa` ce " +
				"INNER JOIN " +
				 "`bodyboardsys`.`campeonato` c ON c.idcampeonato = ce.idcampeonato " +
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
			
			campeonatoEtapaModel = CampeonatoEtapa_control.carregarCampeonatoMaisEtapaResultSet(res);
			conn.close();
			return campeonatoEtapaModel;
			
		} catch (Exception e) {
			return null;
		}    	
		
	}
	
	public boolean editar( CampeonatoEtapa_model campeonatoEtapaModel ){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();
    	Statement stmt = null;
    	
    	DateFormat dataFormataData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	
    	int res = 0;
    	
    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( campeonatoEtapaModel.getIdcampeonatoetapa() != 0 ){
    		String valor = " idcampeonatoetapa=";
    		valor += Integer.toString( campeonatoEtapaModel.getIdcampeonatoetapa() );
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
	       String sql = "UPDATE `bodyboardsys`.`campeonatoetapa` " +
	       	"SET " +
		    "nome = '"+campeonatoEtapaModel.getNomeEtapa()+"'," +
		    "praia = '"+campeonatoEtapaModel.getPraia()+"'," +
		    "cidade = '"+campeonatoEtapaModel.getCidade()+"'," +
		    "idestado = '"+campeonatoEtapaModel.getIdestado()+"'," +
		    "idpais = '"+campeonatoEtapaModel.getIdpais()+"'," +
		    "dataInicio = '"+dataFormataData.format( campeonatoEtapaModel.getDataInicioEtapa() )+"'," +
		    "dataEncerramento = '"+dataFormataData.format( campeonatoEtapaModel.getDataEncerramentoEtapa() )+"' " +
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
     * Funcao que atualiza o status de uma etpa
     * @param	int idcampeonatoEtapa
     * @param	int status
     * @return	boolean
     *  */	
	public boolean editarStatusEtapa( int idcampeonatoEtapa,int status ){
		
		Connection conn = null;	
		Statement stmt = null;
		int res = 0;
		
		try{
			conn = BDConexao_dao.conectar();
			conn.setAutoCommit(false);
			
			//Filtro da minha query
			String filtro = ""; 
			
			if( idcampeonatoEtapa != 0 ){
				String valor = " idcampeonatoetapa=";
				valor += Integer.toString( idcampeonatoEtapa );
				filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
			}
			
			String sql = "UPDATE `bodyboardsys`.`campeonatoetapa` " +
			"SET " +
			"status = '"+status+"' " +
			filtro;
			
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
			
			//fecho a conexao do BD
			conn.close();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}  	
		
	}
	
	/**
     * Funcao que carrega uma lista de Atleta_model completo inscritos em uma etapa de campeonato
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	ArrayList<Atleta_model> listaAtletaInscrito
     *  */
    public ArrayList<Atleta_model> listarAtletaInscrito( CampeonatoEtapa_model campeonatoEtapaModel ){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();
    	ArrayList<Atleta_model> listaAtletaInscrito = null;
    	
    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( campeonatoEtapaModel.getIdcampeonatoetapa() != 0 ){
    		String valor = " idcampeonatoetapaia=";
    		valor += Integer.toString(campeonatoEtapaModel.getIdcampeonatoetapa());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//caso nao tenha filtro para esta consulta retorno a lista vazia
    	if(filtro == "" && filtro == null){
    		return listaAtletaInscrito;
    	}
    	
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT" +
    			 " * " +
    			"FROM " +
    			 "`bodyboardsys`.`inscricaoatleta`" +
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
    		
    		//instancio o obj Atleta_control
    		AtletaController atletaControl = new AtletaController();
    		listaAtletaInscrito = atletaControl.carregarListaUsuarioAtletaInscritoResultSet(res);
    		conn.close();
    		return listaAtletaInscrito;
    		
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    	
    }
    
    /**
     * Funcao que carrega uma lista de Juiz_model completo inscritos em uma etapa de campeonato
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	ArrayList<Juiz_model> listaJuizInscrito
     **/
    public ArrayList<Juiz_model> listarJuizInscrito( CampeonatoEtapa_model campeonatoEtapaModel ){
    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();
    	ArrayList<Juiz_model> listaJuizInscrito = null;
    	
    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( campeonatoEtapaModel.getIdcampeonatoetapa() != 0 ){
    		String valor = " idcampeonatoetapa=";
    		valor += Integer.toString(campeonatoEtapaModel.getIdcampeonatoetapa());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//caso nao tenha filtro para esta consulta retorno a lista vazia
    	if(filtro == "" && filtro == null){
    		return listaJuizInscrito;
    	}
    	
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT" +
    	" * " +
    	"FROM " +
    	"`bodyboardsys`.`inscricaojuiz`" +
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
    		
    		//instancio o obj Atleta_control
    		JuizController juizControl = new JuizController();
    		listaJuizInscrito = juizControl.carregarListaUsuarioJuizInscritoResultSet(res);
    		conn.close();
    		return listaJuizInscrito;
    		
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    	
    }
    
    /**
     * Funcao que carrega um campeonato etapa inteiro com todas as baterias apartir da instancia atual de CampeonatoEtapa_model
     * @param	CampeonatoEtapa_model campeonatoEtapaModel
     * @return	CampeonatoEtapa_model campeonatoEtapaModel
     *  */
	public ResultSet carregarCampeonatoEtapaInteiro( CampeonatoEtapa_model campeonatoEtapaModel ){
		
		Connection conn = null;	
		conn = BDConexao_dao.conectar();
		
		//Filtro da minha query
		String filtro = ""; 
		
		if( campeonatoEtapaModel.getIdcampeonato() != 0 ){
			String valor = " ce.idcampeonato=";
			valor += Integer.toString(campeonatoEtapaModel.getIdcampeonato());
			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
		}
		
		if( campeonatoEtapaModel.getIdcampeonatoetapa() != 0 ){
			String valor = " ce.idcampeonatoetapa=";
			valor += Integer.toString(campeonatoEtapaModel.getIdcampeonatoetapa());
			filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
		}
		
		//preparando a query
		Statement query = null;
		String sql = "SELECT " +
				 "* " +
				"FROM " +
				 "`bodyboardsys`.`campeonatoetapa` ce " +
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

			conn.close();
			return res;
			
		} catch (Exception e) {
			return null;
		}    	
		
	}

}
