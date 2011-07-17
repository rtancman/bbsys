package br.com.bbsys.control.navigation;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private final Result result;
	private final NavigationController navigation;

	public IndexController(Result result) {
		this.result = result;
		this.navigation = new NavigationController();
	}
	
    @Get
    @Path("/")
    public void index() {
    	result.include("htmlHeader", this.navigation.defaultHtmlHeader);
    	result.include("headeraux", this.navigation.defaultHeader);
    	result.include("footer", this.navigation.defaultFooter);
    }

}