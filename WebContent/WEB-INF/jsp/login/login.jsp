<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/bbsys/images/icones/favicon.ico">
<link rel="stylesheet" type="text/css" href="/bbsys/css/base.css">
<link rel="stylesheet" type="text/css" href="/bbsys/css/form.css">
<title>BodyboardSys - Sistema para gerenciar campeonato de bodyboard.</title>
<link rel="stylesheet" type="text/css" href="/bbsys/css/Home.css">
<link rel="stylesheet" type="text/css" href="/bbsys/css/login/login.css">
</head>
<body>
	
	<img src="/bbsys/images/onda1.jpg" class="bg" />
	
<div id="content">
	<div id="meioHomeLogin">
		
		<div id="meioForm">
			<div id="imgLogo">
				<img id="logoText" src="/bbsys/images/logo_text_l.jpg"/>
				<img id="logo" src="/bbsys/images/logo.gif"/>
			</div>
			<div class="fundo">

			<div id="formLoginContent" style="margin-left: -343px;margin-top: 60px;">
			<div id="feedbackLogin"></div>
			<form action="/bbsys/login" method="post">
				<div id="camposForm">
					<ul>
						<li class="formli">E-MAIL: </li>
						<li class="formlinput"><input type="text" class="txform" name="usuario.email" value="${usuario.email}" ></input></li>				
					</ul>
					<ul>
						<li class="formli">SENHA: </li>
						<li class="formlinput"><input type="password" class="txform" name="usuario.senha" value="${usuario.senha}"></input></li>				
					</ul>
					<input class="fl pd1" type="image" src="/bbsys/images/btSubmit.jpg" id="submit" name="enviar" value="enviar" onclick="javascript:$('loader').show();"></input>	
					<a href="#" id="esqueciSenha" >Esqueci minha senha</a>
					<br />
					<a href="#" id="naoECadastrado" >Atleta não é Cadastrado? clique aqui.</a>
				</div>
						
					
			</form>
			</div>
			</div>
		</div>
	</div>
	
</div>
	
</body>
</html>