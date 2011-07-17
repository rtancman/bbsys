package br.com.bbsys.control.usuario;

import br.com.bbsys.control.navigation.NavigationController;
import br.com.bbsys.model.usuario.Atleta;
import br.com.bbsys.business.usuario.AtletaBusiness;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class AtletaController {
	
	private final Result result;
	private Atleta atleta;
	private AtletaBusiness atletaBusiness;
	private final NavigationController navigation;
	
	public AtletaController(Result result, AtletaBusiness atletaBusiness) {
		this.result = result;
		this.atletaBusiness = atletaBusiness;
		this.navigation = new NavigationController();
	}
	
}
