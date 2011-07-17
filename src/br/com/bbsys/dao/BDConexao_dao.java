/**
 * Pacote da classe
 */
package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Raffael
 * @category Classe para conexao de banco
 * @version 1.0
 */
public final class BDConexao_dao {

	public static Connection conectar(){
		Connection conn = null;
		// TODO m�todo construtor quando chamado j� ir� se conectar ao banco de dados
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DATABASE_URL = "jdbc:mysql://localhost:3306/bodyboardsys";  
		String USERNAME= "root";  
		String PASSWORD= "1234";
		
		//tratamento para saber se tenho uma conexao de banco
		try {
			Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
		} catch (ClassNotFoundException e) {  
			System.out.println("N�o foi possivel encontrar o driver de banco: " + e.getMessage()); 
        } catch (InstantiationException e) {  
        	System.out.println(e.getMessage());  
        } catch (IllegalAccessException e) {  
        	System.out.println(e.getMessage());
        } catch(SQLException e){
            System.out.println("Erro ao conectar com o banco: " + e.getMessage());
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }

        return conn;
	}
	
	public static String adicionaFiltro( String filtro, String valor, String condicao ){

		if( condicao.isEmpty() == true ){
			condicao = " AND ";
		}
		
		if( filtro.isEmpty() == true){
			filtro = " WHERE ";
			condicao = "";
		}
		
		if( valor.isEmpty() == true ){
			filtro = "";
		}else{
			filtro += condicao + valor; 
		}
		
		return filtro;
	
	}
	
	/**
	 * Funcao para adicionar ordenacao para uma consulta sql
	 * @param String ordem
	 * @param String valor
	 * @param String ordenacao
	 * @return String
	 * */
	public static String adicionaOrdem( String ordem, String valor, String ordenacao ){
		
		String condicao = "";
		
		if( ordem.isEmpty() == true){
			ordem = " ORDER BY";
			condicao = "";
		}else{
			ordem += ordem;
			condicao = ",";
		}
		
		if( valor.isEmpty() == true ){
			ordem = "";
		}else{
			ordem += condicao + " " + valor + " " + ordenacao; 
		}
		
		return ordem;
		
	}
}
