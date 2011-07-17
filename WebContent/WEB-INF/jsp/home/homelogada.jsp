<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/bbsys/images/icones/favicon.ico">
<script type="text/javascript" src="/bbsys/javascript/prototype.js"></script>
<script type="text/javascript" src="/bbsys/javascript/Base.js"></script>
<link rel="stylesheet" type="text/css" href="/bbsys/css/base.css">
<link rel="stylesheet" type="text/css" href="/bbsys/css/form.css">
<title>BodyboardSys - Sistema para gerenciar campeonato de bodyboard.</title>
<link rel="stylesheet" type="text/css" href="/bbsys/css/Home.css">
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
			<br/><br/><br/><br/>
			${usuarioSession.usuario.nome}<br/><br/>
			${usuarioSession.usuario.email}
			</div>
			</div>
		</div>
	</div>
	
</div>
	
</body>
</html>