package br.com.bbsys.control.usuario;

import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.bbsys.dao.usuario.Gerente_dao;
import br.com.bbsys.model.usuario.Gerente_model;

public class GerenteController extends UsuarioController {

	/**
	 * 
	 * @category Fun��o para listar os gerentes cadastrados no sistema
	 * @author Raffael
	 * @param String nomeBusca - lista por nome buscado
	 * @param String campoOrdem - ordena pelo campo passado
	 * @return ArrayList<Gerente_model>
	 * 
	 * */
    public static ArrayList<Gerente_model> listar(String nomeBusca, String campoOrdem){
    	
    	Gerente_dao gerenteDao = new Gerente_dao();
    	
    	ArrayList<Gerente_model> listaGerente = null;
    	
    	listaGerente = Gerente_dao.listar(nomeBusca,campoOrdem);
    	
    	if(listaGerente != null && listaGerente.size() > 0){
    		return listaGerente;
    	}
    	
		return null;
		
	}
    
    /**
	 * @category Fun��o para cadastrar um gerente
	 * @author Raffael
	 * @param int usuarioID - id do usuario
	 * @return boolean
	 * */
	public boolean cadastrar( int usuarioID ){
		
		boolean resultado = false;
		
		Gerente_dao gerenteDao = new Gerente_dao();
		
		resultado = gerenteDao.cadastrar(usuarioID);
		
		return resultado;
	}
	
	/**
     * Funcao que carrega um Gerente_model completo apartir da instancia atual
     * @param	Gerente_model gerenteModel
     * @return	Gerente_model gerenteModel
     *  */
    public Gerente_model carregarUsuarioGerente( Gerente_model gerenteModel ){
    	    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Gerente_model gerenteModelAux = new Gerente_model(); 
    	Gerente_dao gerenteDao = new Gerente_dao();
    	
    	gerenteModelAux = gerenteDao.carregarUsuarioGerente(gerenteModel);
    	
    	return gerenteModelAux;
    	
    }
    
    /**
     * Funcao que seta a instancia de Gerente_model apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	Gerente_model gerenteModel
     *  */
    public static Gerente_model carregarUsuarioGerenteResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Gerente_model gerenteModel = new Gerente_model(); 
    	try{
	    	while(res.next()){
	    		//obj usuario
	    		gerenteModel.setId(res.getInt("u.idusuario"));
	    		gerenteModel.setNome(res.getString("u.nome"));
			    gerenteModel.setEmail(res.getString("u.email"));         
			    gerenteModel.setSenha(res.getString("u.senha"));         
			    gerenteModel.setSexo(res.getString("u.sexo"));         
			    gerenteModel.setTelefone(res.getInt("u.telefone"));         
			    gerenteModel.setDataNascimento(res.getDate("u.dataNascimento"));         
			    gerenteModel.setDataCadastro(res.getDate("u.dataCadastro"));         
			    gerenteModel.setTipoUsuario(res.getInt("u.idtipousuario"));         
			    gerenteModel.setCidade(res.getString("u.idcidadeu"));         
			    //obj gerente
			    gerenteModel.setIdgerente(res.getInt("g.idgerente"));
			    gerenteModel.setDataCadastroGerente(res.getDate("g.dataCadastro"));
			}
	    	return gerenteModel;
    	}catch (Exception e) {
    		return null;
		}	
    	
    }
    
    /**
     * Funcao que seta a um ArraList<Gerente_model> apartir de um obj ResultSet
     * @param	ResultSet res
     * @return	ArraList<Gerente_model>
     *  */
    public static ArrayList<Gerente_model> carregarListaUsuarioGerenteResultSet( ResultSet res ){
    	
    	//Inicio os objetos que ser�o utilizados para este metodo
    	Gerente_model gerenteModel = null;
    	ArrayList<Gerente_model> listaGerenteModel = null;

    	try{
    		
    		listaGerenteModel = new ArrayList<Gerente_model>();
    		
    		while(res.next()){
    			gerenteModel = new Gerente_model(); 
    			//obj usuario
    			gerenteModel.setId(res.getInt("u.idusuario"));
    			gerenteModel.setNome(res.getString("u.nome"));
    			gerenteModel.setEmail(res.getString("u.email"));         
    			gerenteModel.setSenha(res.getString("u.senha"));         
    			gerenteModel.setSexo(res.getString("u.sexo"));         
    			gerenteModel.setTelefone(res.getInt("u.telefone"));         
    			gerenteModel.setDataNascimento(res.getDate("u.dataNascimento"));         
    			gerenteModel.setDataCadastro(res.getDate("u.dataCadastro"));         
    			gerenteModel.setTipoUsuario(res.getInt("u.idtipousuario"));         
    			gerenteModel.setCidade(res.getString("u.idcidadeu"));         
			    //obj gerente
			    gerenteModel.setIdgerente(res.getInt("g.idgerente"));
			    gerenteModel.setDataCadastroGerente(res.getDate("g.dataCadastro"));

    			//adiciono o obj na lista
    			listaGerenteModel.add(gerenteModel);
    		}
    		return listaGerenteModel;
    	}catch (Exception e) {
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
		
		boolean resultado = Gerente_dao.removerGerente(idUsaurio);
		
		return resultado;
	}  
	
}
