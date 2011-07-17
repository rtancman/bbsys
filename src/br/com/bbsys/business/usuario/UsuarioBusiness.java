/**
 * 
 */
package br.com.bbsys.business.usuario;

import javax.persistence.NoResultException;

import br.com.bbsys.control.home.HomeController;
import br.com.bbsys.dao.usuario.UsuarioDao;
import br.com.bbsys.model.usuario.Usuario;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioBusiness {

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	
	public UsuarioBusiness(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	public Usuario login(Usuario usuario) throws Exception {
		this.usuario = usuarioDao.loadByEmailAndSenha(usuario.getEmail(),usuario.getSenha());
		return this.usuario;
	}
	
}
