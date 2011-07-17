package DAO.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Control.Usuario.Usuario_control;
import Control.Usuario.Atleta_control;
import DAO.BDConexao_dao;
import Model.Campeonato.CampeonatoEtapa_model;
import Model.Campeonato.Campeonato_model;
import Model.Usuario.Atleta_model;
import Model.Usuario.Usuario_model;

public class Atleta_dao {

	/**
	 * @category Fun��o para buscar um atleta que esta inscrito em algum campeonato no sistema
	 * @author Raffael
	 * @param int id - id do atleta
	 * @return ArrayList<CampeonatoEtapa_model>
	 * */
	public ArrayList<CampeonatoEtapa_model> atletaInscritoEmCampeonato( int id ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String filtro = "";
		ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa = new ArrayList<CampeonatoEtapa_model>();
		
		//filtros para buscar um atleta inscrito em algum campeonato
    	if(id != 0){

    		filtro += " WHERE ia.idatletaia="+id;

    	}
    	
		try{
			
			conn = BDConexao_dao.conectar();

			String sql = "SELECT " +
					"ia.dataCadastro, " +
					"ce.*," +
					"p.nome," +
					"e.nome " +
					"FROM `bodyboardsys`.`inscricaoatleta` ia " +
					"INNER JOIN `bodyboardsys`.`campeonatoetapa` ce ON ce.idcampeonatoetapa = ia.idcampeonatoetapaia " +
					"INNER JOIN `bodyboardsys`.`pais` p ON p.idpais = ce.idpais " +
					"INNER JOIN `bodyboardsys`.`estado` e ON e.idestado = ce.idpais";
			
			if(filtro != ""){
				sql = sql + filtro;
			}
			
			pstmt = conn.prepareStatement(sql);

			res = pstmt.executeQuery();

			while(res.next()){
				CampeonatoEtapa_model campeonatoEtapa = new CampeonatoEtapa_model();
				campeonatoEtapa.setIdcampeonatoetapa(res.getInt("ce.idcampeonatoetapa"));
				campeonatoEtapa.setNome(res.getString("ce.nome"));
				campeonatoEtapa.setPraia(res.getString("ce.praia"));
				campeonatoEtapa.setCidade(res.getString("ce.cidade"));
				campeonatoEtapa.setDataInicioEtapa(res.getDate("ce.dataInicio"));
				campeonatoEtapa.setDataEncerramentoEtapa(res.getDate("ce.dataEncerramento"));
				campeonatoEtapa.setStatus(res.getInt("ce.status"));
				listaCampeonatoEtapa.add(campeonatoEtapa);
	        }
						
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
	
	/**
	 * @category Fun��o para buscar a lista de campeonatos que um atleta nao esta inscrito
	 * @author Raffael
	 * @param int id - id do atleta
	 * @return ArrayList<CampeonatoEtapa_model>
	 * */
	public ArrayList<CampeonatoEtapa_model> atletaNaoInscritoEmCampeonato( int id ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa = new ArrayList<CampeonatoEtapa_model>();
		
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			String sql = "SELECT " +
			 "ia.idcampeonatoetapaia " +
			"FROM `bodyboardsys`.`inscricaoatleta` ia " +
			"WHERE ia.idatletaia="+id;
			
			pstmt = conn.prepareStatement(sql);
			
			res = pstmt.executeQuery();
			
			String sql2 = "SELECT " +
			"ce.*," +
			"p.nome," +
			"e.nome " +
			"FROM `bodyboardsys`.`campeonatoetapa` ce " +
			"INNER JOIN `bodyboardsys`.`pais` p ON p.idpais = ce.idpais " +
			"INNER JOIN `bodyboardsys`.`estado` e ON e.idestado = ce.idpais";			
			
			//crio as variaveis auxiliares
			String filtro = "";
			int count = 1;
			
			while(res.next()){
				if( count == 1 ){
					filtro += " ce.idcampeonatoetapa <> "+ res.getInt("ia.idcampeonatoetapaia");
				}
				filtro += " AND ce.idcampeonatoetapa <> "+ res.getInt("ia.idcampeonatoetapaia");
				count += 1;
			}
			
			if(filtro != ""){
				sql2 = sql2 + " WHERE" + filtro + " order by ce.idcampeonatoetapa";
			}
			
			pstmt = conn.prepareStatement(sql2);
			
			res = pstmt.executeQuery();
			
			while(res.next()){
				CampeonatoEtapa_model campeonatoEtapa = new CampeonatoEtapa_model();
				campeonatoEtapa.setIdcampeonatoetapa(res.getInt("ce.idcampeonatoetapa"));
				campeonatoEtapa.setNome(res.getString("ce.nome"));
				campeonatoEtapa.setPraia(res.getString("ce.praia"));
				campeonatoEtapa.setCidade(res.getString("ce.cidade"));
				campeonatoEtapa.setDataInicioEtapa(res.getDate("ce.dataInicio"));
				campeonatoEtapa.setDataEncerramentoEtapa(res.getDate("ce.dataEncerramento"));
				campeonatoEtapa.setStatus(res.getInt("ce.status"));
				listaCampeonatoEtapa.add(campeonatoEtapa);
			}
			
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
	
	/**
	 * @category Fun��o para buscar um atleta cadastrado no sistema
	 * @author Raffael
	 * @param String nomeBusca - nome a ser buscado
	 * @param int id - id a serbuscado
	 * @return Atleta_model
	 * */
	public Atleta_model buscar( String nomeBusca, int id ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String filtro = "";
		Atleta_model atletaModel = null;
		
		//filtros para buscar um atleta
    	if(nomeBusca != null){
    		filtro += " WHERE u.nome ="+nomeBusca;
    	}
    	
    	if(id != 0){
    		if( nomeBusca != null ){
    			filtro += " AND a.idatleta="+id;
    		}else{
    			filtro += " WHERE a.idatleta="+id;
    		}
    	}
    	
		try{
			
			conn = BDConexao_dao.conectar();

			String sql = "SELECT u.*,a.* " +
					" FROM " +
					" `bodyboardsys`.`usuario` u " +
					"INNER JOIN `bodyboardsys`.`atleta` a on a.idusuarioa = u.idusuario";
			
			if(filtro != ""){
				sql = sql + filtro;
			}
			
			pstmt = conn.prepareStatement(sql);

			res = pstmt.executeQuery();

			if(res.next()){
			   atletaModel = new Atleta_model();
			   atletaModel.setId(res.getInt("idusuario"));
			   atletaModel.setidatleta(res.getInt("idatleta"));
			   atletaModel.setNome(res.getString("nome"));
			   atletaModel.setEmail(res.getString("email"));
	        }
						
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
				return atletaModel;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
	}
	
	/**
	 * @category Fun��o para listar os atletas cadastrados no sistema
	 * @author Raffael
	 * @param String nomeBusca - lista por nome buscado
	 * @param String campoOrdem - ordena pelo campo passado
	 * @return ArrayList<Atleta_model>
	 * 
	 * */
	
	public static ArrayList<Atleta_model> listar(String nomeBusca, String campoOrdem){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Atleta_model> listaAtleta = new ArrayList<Atleta_model>();
		String filtro = "";
		
		//filtros para listar um atleta
    	if(nomeBusca != null){
    		filtro += " WHERE u.nome like '%"+nomeBusca+"%'";
    	}
    	if(campoOrdem != null){
    		
    		if( campoOrdem.equals("idatleta") ){
    			filtro += " ORDER BY a."+campoOrdem;
    		}else{
    			filtro += " ORDER BY u."+campoOrdem;
    		}
    		
    	}
		
		try{
			
			conn = BDConexao_dao.conectar();

			String sql = "SELECT u.*,a.* " +
			" FROM " +
			" `bodyboardsys`.`usuario` u " +
			"INNER JOIN `bodyboardsys`.`atleta` a on a.idusuarioa = u.idusuario";
			
			//se tenho um filtro adiciono na query
			if(filtro != ""){
				sql = sql + filtro;
			}
			
			pstmt = conn.prepareStatement(sql);	

			res = pstmt.executeQuery();
			
			while(res.next()){
				Atleta_model atletaModel = new Atleta_model();
			    atletaModel.setId(res.getInt("u.idusuario"));
			    atletaModel.setidatleta(res.getInt("a.idatleta"));
			    atletaModel.setNome(res.getString("u.nome"));
			    atletaModel.setEmail(res.getString("u.email"));                     
			    atletaModel.setdataCadastroAtleta(res.getDate("a.dataCadastro"));                     
			    listaAtleta.add(atletaModel);  
	        }
			
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
				return listaAtleta;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
		
	}
	
	/**
	 * @category Fun��o para inscrever um atleta em uma etapa de campeonato no sistema
	 * @author Raffael
	 * @param int idAtleta
	 * @param int idCampeonato
	 * @return Atleta_model
	 * */
	public boolean inscricao( int idAtleta, int idCampeonato ){
			
		Connection conn = null;
		Statement stmt = null;
		int res = 0;
		boolean rs = false;			
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			//desabilito o autocommit
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO " +
					 "`bodyboardsys`.`inscricaoatleta` " +
					 "(`idinscricaoatleta`, " +
					 "`idatletaia`, " +
					 "`idcampeonatoetapaia`, " +
					 "`dataCadastro`) " +
					"VALUES " +
					"(NULL, "+idAtleta+", "+idCampeonato+", NULL);";
			
			stmt = conn.createStatement();
			
			res = stmt.executeUpdate(sql);
			
			if(res != 0){
				rs = true;
				conn.commit();
			}else{
				rs = false;
				conn.rollback();
			}
							
		}catch(SQLException e){
			//System.out.println("Erro ao conectar com o banco: " + e.getMessage());
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
			conn.rollback();
			return false;
		}catch(Exception e2){
			try {
				conn.rollback();
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();
				return false;
			}
		}finally{
			
			try {
				stmt.close();
				conn.close();
				conn = null;				
				stmt = null;
				return rs;
			} catch (SQLException e4) {
				e4.printStackTrace();
				return false;
			}
			
		}
	}
	
	/**
	 * @category Fun��o para remover um atleta da etapa de campeonato no sistema
	 * @author Raffael
	 * @param int idAtleta
	 * @param int idCampeonato
	 * @return Atleta_model
	 * */
	public boolean removerInscricao( int idAtleta, int idCampeonato ){
		
		Connection conn = null;
		Statement stmt = null;
		int res = 0;
		boolean rs = false;			
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			//desabilito o autocommit
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM `bodyboardsys`.`inscricaoatleta` " +
					"where " +
					 "`idatletaia`="+idAtleta+" " +
					"AND " +
					 "idcampeonatoetapaia ="+idCampeonato;
			
			stmt = conn.createStatement();
			
			res = stmt.executeUpdate(sql);
			
			if(res != 0){
				rs = true;
				conn.commit();
			}else{
				rs = false;
				conn.rollback();
			}
			
		}catch(SQLException e){
			//System.out.println("Erro ao conectar com o banco: " + e.getMessage());
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
			conn.rollback();
			return false;
		}catch(Exception e2){
			try {
				conn.rollback();
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();
				return false;
			}
		}finally{
			
			try {
				stmt.close();
				conn.close();
				conn = null;				
				stmt = null;
				return rs;
			} catch (SQLException e4) {
				e4.printStackTrace();
				return false;
			}
			
		}
	}
	
    /**
	 * @category Fun��o para remover um atleta
	 * @author Raffael
	 * @param int idUsuario
	 * @return boolean
	 * */
	public static boolean removerAtleta( int idUsaurio ){
		
		Connection conn = null;
		Statement stmt = null;
		int res = 0;
		boolean rs = false;			
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			//desabilito o autocommit
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM `bodyboardsys`.`atleta` " +
			"where " +
			"`idusuarioa`="+idUsaurio+";";
			
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
	
	/**
	 * @category Fun��o para cadastrar um atleta
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
			
			String sql = "INSERT INTO " +
			 "`bodyboardsys`.`atleta` " +
			 "(`idatleta`, `idusuarioa`, `dataCadastro`) " +
			"VALUES " +
			"(NULL, "+usuarioID+", NULL);";
	
			stmt = conn.createStatement();
			
			res = stmt.executeUpdate(sql);
			
			if(res != 0){
				rs = true;
				conn.commit();
			}else{
				rs = false;
				conn.rollback();
			}
			
		}catch(SQLException e){
			//System.out.println("Erro ao conectar com o banco: " + e.getMessage());
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
			conn.rollback();
			return false;
		}catch(Exception e2){
			try {
				conn.rollback();
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();
				return false;
			}
		}finally{
			
			try {
				stmt.close();
				conn.close();
				conn = null;				
				stmt = null;
				return rs;
			} catch (SQLException e4) {
				e4.printStackTrace();
				return false;
			}
			
		}

	}
	
	/**
     * Funcao que carrega um Atleta_model completo apartir da instancia atual
     * @param	Atleta_model atletaModel
     * @return	Atleta_model atletaModel
     *  */
    public Atleta_model carregarUsuarioAtleta( Atleta_model atletaModel ){
    	    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();

    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( atletaModel.getId() != 0 ){
    		String valor = " u.idusuario=";
    		valor += Integer.toString(atletaModel.getId());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	if( atletaModel.getidatleta() != 0 ){
    		String valor = " a.idatleta=";
    		valor += Integer.toString(atletaModel.getidatleta());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	if( atletaModel.getEmail() != null && atletaModel.getEmail() != ""){
    		String valor = " u.email=" + atletaModel.getEmail();
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT " +
    			 "u.*," +
    			 "a.*" +
    			" FROM " +
    			 "`bodyboardsys`.`usuario` u " +
    			"INNER JOIN " +
    			 "`bodyboardsys`.`atleta` a ON a.idusuarioa = u.idusuario" +
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
    		atletaModel = AtletaController.carregarUsuarioAtletaResultSet(res);
    		conn.close();
    		return atletaModel;
    	} catch (Exception e) {
    		return null;
    	}    	
    	
    	
    }
	
}
