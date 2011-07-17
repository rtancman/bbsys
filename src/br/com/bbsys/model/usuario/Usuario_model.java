/**
 * 
 */
package br.com.bbsys.model.usuario;
import java.util.Date;
import java.sql.Array;

/**
 * @author Raffael
 * @category Classe Modelo do objeto Usuario
 * @version 1.0
 */
public class Usuario_model {
	 /**
	  * atributo de identificacao dessa classe 
	  * */
    private int id;
    
	 /**
	  * atributo nome 
	  * */
    private String nome;
    
	 /**
	  * atributo sexo
	  * */
    private String sexo;
    
	 /**
	  * atributo telefone
	  * */
    private int telefone;
    
	 /**
	  * atributo data nascimento
	  * */
    private Date dataNascimento;
    
	 /**
	  * atributo que identifica o endereco deste usuario
	  * */
    private String cidade;
    
    /**
     * atributo que identifica o estado deste usuario
     * */
    private int estado;
    
    /**
     * atributo que identifica o pais deste usuario
     * */
    private int pais;
    
	 /**
	  * atributo email - este identifica o login
	  * */
    private String email;
    
	 /**
	  * atributo senha - este identifica a senha
	  * */
    private String senha;
    
	 /**
	  * atributo data de cadastro
	  * */
    private Date dataCadastro;
    
	 /**
	  * atributo data de cadastro
	  * */
    private int tipoUsuario;
   
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * 
	 */
	public Usuario_model(String nome, String sexo, String telefone, Date dataNascimento, String tipoUsuario, String cidade, String pais,String estado,String email, String senha) {
		// TODO Auto-generated constructor stub
		this.setNome(nome);
		this.setSexo(sexo);
		this.setTelefone(Integer.parseInt(telefone));
		this.setDataNascimento(dataNascimento);
		this.setTipoUsuario(Integer.parseInt(tipoUsuario));
		this.setCidade(cidade);
		this.setPais(Integer.parseInt(pais));
		this.setEstado(Integer.parseInt(estado));
		this.setEmail(email);
		this.setSenha(senha);
	}
	
	public Usuario_model(){
		
	}
	
}
