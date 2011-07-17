package br.com.bbsys.control.core;

import br.com.bbsys.business.usuario.UsuarioBusiness;
import br.com.bbsys.control.home.HomeController;
import br.com.bbsys.dao.usuario.UsuarioDao;
import br.com.bbsys.model.usuario.Usuario;
import br.com.bbsys.session.usuario.UsuarioSession;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class LoginController {

    private Result result;
    private UsuarioBusiness usuarioBusiness;
    private UsuarioSession userSession;

    public LoginController(Result result, UsuarioBusiness usuarioBusiness, UsuarioSession userSession) {
        this.result = result;
        this.usuarioBusiness = usuarioBusiness;
        this.userSession = userSession;
    }

    @Get
    @Path("/login")
    public void login() {}

    @Post
    @Path("/login")
    public void login(Usuario usuario) {
        try {
            Usuario user = usuarioBusiness.login(usuario);
            userSession.setUsuario(user);
            result.redirectTo(HomeController.class).homelogada();
        } catch (Exception e) {
            result.include("messageException", e.getMessage());
            //result.use(Results.json()).from(e.getMessage()).serialize();
            result.forwardTo(this).login();
        }
    }

    @Path("/logout")
    public void logout() {
        userSession.setUsuario(null);
        result.redirectTo(this).login();
    }

}