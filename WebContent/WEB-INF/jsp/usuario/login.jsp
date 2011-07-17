<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/Bodyboardsys/Static/Img/icones/favicon.ico">
<script type="text/javascript" src="/bbsys/javascript/prototype.js"></script>
<script type="text/javascript" src="/bbsys/javascript/Base.js"></script>
<link rel="stylesheet" type="text/css" href="/bbsys/css/base.css">
<link rel="stylesheet" type="text/css" href="/bbsys/css/form.css">
<title>BodyboardSys - Sistema para gerenciar campeonato de bodyboard.</title>
<link rel="stylesheet" type="text/css" href="/bbsys/css/Home.css">
<link rel="stylesheet" type="text/css" href="/bbsys/css/login/login.css">
</head>
<body>
	
	<img src="/bbsys/images/onda1.jpg" class="bg" />
	
<div id="content">
	<div class="menu-top">
		<div id="menu-top-widht">
			<div class="menu-left">
				<img alt="bbsys - logo" src="/bbsys/images/logo_sys_s_wide.jpg"/>
			</div>
			<div class="menu-right">
				aaaa direita
			</div>
		</div>
	</div>
	<div id="meioHomeLogin">
		
		<div id="meioForm">
			<div id="imgLogo">
				<img id="logoText" src="/Bodyboardsys/Static/Img/logo_text_l.jpg"/>
				<img id="logo" src="/Bodyboardsys/Static/Img/logo.gif"/>
			</div>
			<div class="fundo">

			<div id="formLoginContent" style="margin-left: -343px;margin-top: 60px;">	
			<fmt:message key="campo.email"></fmt:message>
				
			<form action="EfetuarLogin" method="post">
				<div id="camposForm">
					<ul>
						<li class="formli">E-MAIL: </li>
						<li class="formlinput"><input type="text" class="txform" name="usuario.email" value="${usuario.email}" ></input></li>				
					</ul>
					<ul>
						<li class="formli">SENHA: </li>
						<li class="formlinput"><input type="password" class="txform" name="usuario.senha" value="${usuario.senha}"></input></li>				
					</ul>
					<input class="fl pd1" type="image" src="/Bodyboardsys/Static/Img/btSubmit.jpg" id="submit" name="enviar" value="enviar" onclick="javascript:$('loader').show();"></input>	
					<a href="/Bodyboardsys/ChecarLogin.jsp" id="esqueciSenha" >Esqueci minha senha</a>
					<br />
					<a href="/Bodyboardsys/Usuario/Cadastro/UsuarioNaoLogadoCadastro.jsp" id="naoECadastrado" >Atleta não é Cadastrado? clique aqui.</a>
				</div>
						
					
			</form>
			</div>
			</div>
		</div>
	</div>
	
</div>
	
</body>
</html>