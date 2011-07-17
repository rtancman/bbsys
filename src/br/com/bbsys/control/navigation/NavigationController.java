package br.com.bbsys.control.navigation;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class NavigationController {

	public String defaultHtmlHeader = "" +
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
			"<meta name=\"editoria\" content=\"bodyboardsys\" />" +
			"<meta name=\"language\" content=\"portuguese\" />" +
			"<meta name=\"robots\" content=\"all\" />" +
			"<meta name=\"revist\" content=\"1 days\" />" +
			"<title>BodyboardSys - Sistema para campeonato de bodyboard.</title>" +
			"<meta http-equiv=\"description\" name=\"description\" content=\"Sistema para gerenciamento de campeonato de bodyboard.\" />" +
			"<meta http-equiv=\"keywords\" name=\"keywords\" content=\"bodyboard, campeonato, sistema\" />" +
			"<meta property=\"og:title\" content=\"The Rock\"/>" +
			"<meta property=\"og:type\" content=\"text/html\"/>" +
			"<meta property=\"og:image\" content=\"/bbsys/images/logo.gif\"/>" +
			"<meta property=\"og:url\" content=\"http://www.bbsys.com/\"/>" +
			"<meta property=\"og:site_name\" content=\"bodyboardsys\"/>" +
			"<meta property=\"og:description\" content=\"BodyboardSys - Sistema para gerenciamento de campeonato de bodyboard.\"/>" +
			"<link rel=\"shortcut icon\" href=\"/bbsys/images/icones/favicon.ico\" /> "+
			"<link rel=\"canonical\" href=\"//www.bbsys.com/\"/>" +
			"<link rel=\"stylesheet\" type=\"text/css\" href=\"/bbsys/css/base.css\">" +
			"<link rel=\"stylesheet\" type=\"text/css\" href=\"/bbsys/css/form.css\">";
	
	public String defaultLink = "" +
			"<link rel=\"shortcut icon\" href=\"/bbsys/images/icones/favicon.ico\" /> "+
			"<link rel=\"canonical\" href=\"//www.bbsys.com/\"/>";
	
	public String defaultHeader = "" +
			"<div id=\"cabecalhoSistema\">" +
				"<img alt=\"BodyboardSys - logo\" src=\"/bbsys/images/logo_sys_s_wide.jpg\"/>" +
				"<strong class=\"frPd\"><a class=\"link\" href=\"#\">Atleta não é Cadastrado? clique aqui.</a></strong>" +
			"</div>";
	
	public String defaultFooter = "" +
			"<div id=\"rodape\" style=\"margin-top:20px;\">" +
				"<div style=\"float:right;margin-right: 25px;\">" +
					"<p><a href=\"/Bodyboardsys/Static/Manual_BodyboardSys.pdf\" target=\"_blank\">AJUDA</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\">CONTATO</a></p>" +
				"</div>" +
				"<div style=\"float:left;\">" +
					"<p>Todos os direitos reservados a bodyboard.sys 2010-2010.</p>" +
				"</div>" +
				"<div style=\"border-bottom:1px solid #FFFFFF; margin-top:20px; margin-bottom:10px;margin-right: 25px;\"></div>" +
				"<div style=\"float:right;margin-right: 25px;\">" +
					"<p>Desenvolvido por: Raffael Tancman</p>" +
				"</div>" +
			"</div>" +
			"<div id=\"loader\" style=\"left:40%;position:absolute;top:50%; display: none;\">" +
				"<center style=\"background-color: #042A74;\">" +
				"<img alt=\"Carregando\" src=\"/Bodyboardsys/Static/Img/ajax-loader.gif\">" +
				"<br /><br /><strong style=\"background-color: #042A74; font:12px arial,helvetica,sans-serif;color:#FFFFFF;\">CARREGANDO</strong>" +
				"</center>" +
			"</div>	";
	
}
