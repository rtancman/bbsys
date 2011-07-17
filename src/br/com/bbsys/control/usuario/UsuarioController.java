/**
 * 
 */
package br.com.bbsys.control.usuario;

import br.com.bbsys.control.navigation.NavigationController;
import br.com.bbsys.dao.usuario.UsuarioDao;
import br.com.bbsys.model.usuario.Usuario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

/**
 * @author Raffael
 * @category Classe Controle do objeto Usuario
 * @version 1.0
 */
@Resource
public class UsuarioController {

	private final Result result;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private final NavigationController navigation;
	
	public UsuarioController(Result result, UsuarioDao usuarioDao) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.navigation = new NavigationController();
	}
	
	@Get
	@Path("/usuario/cadastro-de-atleta")
	public void cadastro() {
		result.include("htmlHeader", this.navigation.defaultHtmlHeader);
    	result.include("headeraux", this.navigation.defaultHeader);
    	result.include("footer", this.navigation.defaultFooter);
	}
	
	@Post
	@Path("/usuario/cadastro-de-atleta")
	public void cadastro(Usuario usuario) {
		result.include("htmlHeader", this.navigation.defaultHtmlHeader);
		result.include("headeraux", this.navigation.defaultHeader);
		result.include("footer", this.navigation.defaultFooter);
	}
	
	public void index() {
		result.include("variable", "VRaptor!");
		result.include("erro", "VRaptor!");
	} 
	
	@Path("/usuario/login")
	public Usuario login() {
		this.usuario = new Usuario();
		this.usuario.setEmail("rtancman@gmail.com");
		this.usuario.setSenha("aaaaa");
		return this.usuario;
	}
	
	@Get
	@Post
	@Path("/usuario/efetuar-login")
	public void login(Usuario usuario) {
		try {
			usuario = usuarioDao.loadByEmailAndSenha(usuario.getEmail(),usuario.getSenha());
			result.use(Results.json()).from(usuario).serialize();  
		} catch (Exception e) {
			result.include("msg", e.getMessage());
			result.use(Results.json()).from(e.getMessage()).serialize();
		}
	}
	
    
}
