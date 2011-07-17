package DAO.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.Usuario.Juiz_control;
import DAO.BDConexao_dao;
import Model.Campeonato.CampeonatoEtapa_model;
import Model.Usuario.Juiz_model;

public class Juiz_dao {

	/**
	 * @category Fun��o para buscar um Juiz que esta inscrito em algum campeonato no sistema
	 * @author Raffael
	 * @param int id - id do Juiz
	 * @return ArrayList<CampeonatoEtapa_model>
	 * */
	public ArrayList<CampeonatoEtapa_model> juizInscritoEmCampeonato( int id ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String filtro = "";
		ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa = new ArrayList<CampeonatoEtapa_model>();
		
		//filtros para buscar um juiz inscrito em algum campeonato
    	if(id != 0){

    		filtro += " WHERE ij.idjuiz="+id;

    	}
    	
		try{
			
			conn = BDConexao_dao.conectar();

			String sql = "SELECT " +
					"ij.dataCadastro, " +
					"ce.*," +
					"p.nome," +
					"e.nome " +
					"FROM `bodyboardsys`.`inscricaojuiz` ij " +
					"INNER JOIN `bodyboardsys`.`campeonatoetapa` ce ON ce.idcampeonatoetapa = ij.idcampeonatoetapa " +
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
	 * @category Fun��o para buscar a lista de campeonatos que um Juiz nao esta inscrito
	 * @author Raffael
	 * @param int id - id do juiz
	 * @return ArrayList<CampeonatoEtapa_model>
	 * */
	public ArrayList<CampeonatoEtapa_model> juizNaoInscritoEmCampeonato( int id ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<CampeonatoEtapa_model> listaCampeonatoEtapa = new ArrayList<CampeonatoEtapa_model>();
		
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			String sql = "SELECT " +
			 "ij.idcampeonatoetapa " +
			"FROM `bodyboardsys`.`inscricaojuiz` ij " +
			"WHERE ij.idjuiz="+id;
			
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
					filtro += " ce.idcampeonatoetapa <> "+ res.getInt("ij.idcampeonatoetapa");
				}
				filtro += " AND ce.idcampeonatoetapa <> "+ res.getInt("ij.idcampeonatoetapa");
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
	 * @category Fun��o para buscar um Juiz cadastrado no sistema
	 * @author Raffael
	 * @param String nomeBusca - nome a ser buscado
	 * @param int id - id a serbuscado
	 * @return Juiz_model
	 * */
	public Juiz_model buscar( String nomeBusca, int id ){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String filtro = "";
		Juiz_model juizModel = null;
		
		//filtros para buscar um Juiz
    	if(nomeBusca != null){
    		filtro += " WHERE u.nome ="+nomeBusca;
    	}
    	
    	if(id != 0){
    		if( nomeBusca != null ){
    			filtro += " AND j.idjuiz="+id;
    		}else{
    			filtro += " WHERE j.idjuiz="+id;
    		}
    	}
    	
		try{
			
			conn = BDConexao_dao.conectar();

			String sql = "SELECT u.*,j.* " +
					" FROM " +
					" `bodyboardsys`.`usuario` u " +
					"INNER JOIN `bodyboardsys`.`juiz` j on j.idusuarioj = u.idusuario";
			
			if(filtro != ""){
				sql = sql + filtro;
			}
			
			pstmt = conn.prepareStatement(sql);

			res = pstmt.executeQuery();

			if(res.next()){
			   juizModel = new Juiz_model();
			   juizModel.setId(res.getInt("idusuario"));
			   juizModel.setIdjuiz(res.getInt("idjuiz"));
			   juizModel.setNome(res.getString("nome"));
			   juizModel.setEmail(res.getString("email"));
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
				return juizModel;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
	}
	
	/**
	 * @category Fun��o para listar os juiz cadastrados no sistema
	 * @author Raffael
	 * @param String nomeBusca - lista por nome buscado
	 * @param String campoOrdem - ordena pelo campo passado
	 * @return ArrayList<Juiz_model>
	 * 
	 * */
	
	public static ArrayList<Juiz_model> listar(String nomeBusca, String campoOrdem){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Juiz_model> listaJuiz = new ArrayList<Juiz_model>();
		String filtro = "";
		
		//filtros para listar um juiz
    	if(nomeBusca != null){
    		filtro += " WHERE u.nome like '%"+nomeBusca+"%'";
    	}
    	if(campoOrdem != null){
    		
    		if( campoOrdem.equals("idjuiz") ){
    			filtro += " ORDER BY j."+campoOrdem;
    		}else{
    			filtro += " ORDER BY u."+campoOrdem;
    		}
    		
    	}
		
		try{
			
			conn = BDConexao_dao.conectar();

			String sql = "SELECT u.*,j.* " +
			" FROM " +
			" `bodyboardsys`.`usuario` u " +
			"INNER JOIN `bodyboardsys`.`juiz` j on j.idusuarioj = u.idusuario";
			
			//se tenho um filtro adiciono na query
			if(filtro != ""){
				sql = sql + filtro;
			}
			
			pstmt = conn.prepareStatement(sql);	

			res = pstmt.executeQuery();
			
			while(res.next()){
				Juiz_model juizModel = new Juiz_model();
				juizModel.setId(res.getInt("u.idusuario"));
				juizModel.setIdjuiz(res.getInt("j.idjuiz"));
				juizModel.setNome(res.getString("u.nome"));
				juizModel.setEmail(res.getString("u.email"));
				juizModel.setDataCadastroJuiz(res.getDate("j.dataCadastro"));
			    listaJuiz.add(juizModel);  
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
				return listaJuiz;
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return null;
		
	}
	
	/**
	 * @category Fun��o para inscrever um Juiz em uma etapa de campeonato no sistema
	 * @author Raffael
	 * @param int idJuiz
	 * @param int idCampeonato
	 * @return Juiz_model
	 * */
	public boolean inscricao( int idJuiz, int idCampeonato ){
			
		Connection conn = null;
		Statement stmt = null;
		int res = 0;
		boolean rs = false;			
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			//desabilito o autocommit
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO " +
					 "`bodyboardsys`.`inscricaojuiz` " +
					 "(`idinscricaojuiz`, " +
					 "`idjuiz`, " +
					 "`idcampeonatoetapa`, " +
					 "`dataCadastro`) " +
					"VALUES " +
					"(NULL, "+idJuiz+", "+idCampeonato+", NULL);";
			
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
	 * @category Fun��o para remover um Juiz da etapa de campeonato no sistema
	 * @author Raffael
	 * @param int idJuiz
	 * @param int idCampeonato
	 * @return Juiz_model
	 * */
	public boolean removerInscricao( int idJuiz, int idCampeonato ){
		
		Connection conn = null;
		Statement stmt = null;
		int res = 0;
		boolean rs = false;			
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			//desabilito o autocommit
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM `bodyboardsys`.`inscricaojuiz` " +
					"where " +
					 "`idjuiz`="+idJuiz+" " +
					"AND " +
					 "idcampeonatoetapa ="+idCampeonato;
			
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
	 * @category Fun��o para cadastrar um juiz
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
			 "`bodyboardsys`.`juiz` " +
			 "(`idjuiz`, `idusuarioj`, `dataCadastro`) " +
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
     * Funcao que carrega um Juiz_model completo apartir da instancia atual
     * @param	Juiz_model juizModel
     * @return	Juiz_model juizModel
     *  */
    public Juiz_model carregarUsuarioJuiz( Juiz_model juizModel ){
    	    	
    	Connection conn = null;	
    	conn = BDConexao_dao.conectar();

    	//Filtro da minha query
    	String filtro = ""; 
    	
    	if( juizModel.getId() != 0 ){
    		String valor = " u.idusuario=";
    		valor += Integer.toString(juizModel.getId());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	if( juizModel.getIdjuiz() != 0 ){
    		String valor = " j.idjuiz=";
    		valor += Integer.toString(juizModel.getIdjuiz());
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	if( juizModel.getEmail() != null && juizModel.getEmail() != ""){
    		String valor = " u.email=" + juizModel.getEmail();
    		filtro = BDConexao_dao.adicionaFiltro(filtro, valor, ""); 
    	}
    	
    	//preparando a query
    	Statement query = null;
    	String sql = "SELECT " +
    			 "u.*," +
    			 "j.*" +
    			" FROM " +
    			 "`bodyboardsys`.`usuario` u " +
    			"INNER JOIN " +
    			 "`bodyboardsys`.`juiz` j ON j.idusuarioj = u.idusuario" +
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
    		juizModel = JuizController.carregarUsuarioJuizResultSet(res);
    		conn.close();
    		return juizModel;
    	} catch (Exception e) {
    		return null;
    	}    		
    }
    
    /**
	 * @category Fun��o para remover um juiz
	 * @author Raffael
	 * @param int idUsuario
	 * @return boolean
	 * */
	public static boolean removerJuiz( int idUsaurio ){
		
		Connection conn = null;
		Statement stmt = null;
		int res = 0;
		boolean rs = false;			
		
		try{
			
			conn = BDConexao_dao.conectar();
			
			//desabilito o autocommit
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM `bodyboardsys`.`juiz` " +
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
