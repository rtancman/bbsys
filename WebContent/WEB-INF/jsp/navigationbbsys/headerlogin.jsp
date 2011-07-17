<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
DateFormat dataFormaData = new SimpleDateFormat("dd/MM/yyyy");  
DateFormat dataFormaHora = new SimpleDateFormat("HH:mm:ss");  
Date date = new Date();
%>

<div id="cabecalhoSistema">
	<img alt="BodyboardSys - logo" src="/Bodyboardsys/Static/Img/logo_sys_s_wide.jpg"/>
	<strong class="frPd"><a class="link" href="/Bodyboardsys/Home.jsp">JÁ É CADASTRADO CLIQUE AQUI</a></strong>
</div>