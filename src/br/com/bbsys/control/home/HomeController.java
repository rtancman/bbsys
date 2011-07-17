package br.com.bbsys.control.home;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class HomeController {

	private final Result result;

	public HomeController(Result result) {
		this.result = result;
	}
	
	@Path("/home")
	public void index() {
	} 
	
	@Path("/home-logada")
	public void homelogada() {
	} 
}
