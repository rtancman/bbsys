package br.com.bbsys.session.usuario;

import br.com.bbsys.model.usuario.Usuario;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class UsuarioSession {
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
