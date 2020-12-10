<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ include file="config.jsp" %>
<%
	String jsonResult;
	MySqlDbConnection db = new MySqlDbConnection();
	db.setDbUser(dbUser);
	db.setDbPassword(dbPassword);
	db.setDbName(dbName);

	int activity = 0;
	int week = 0;
	if (request.getParameter("activity") != null && request.getParameter("week") != null) {
        activity = Integer.parseInt(request.getParameter("activity"));
        week = Integer.parseInt(request.getParameter("week"));
    } 


	ShowSpecificationsBrowseServiceJSP service = new ShowSpecificationsBrowseServiceJSP();
	response.setContentType("application/json");
	response.setHeader("Access-Control-Allow-Origin","*");
%>
<%= service.getShowSpecificationsBrowseToJSONJSP(db,activity,week) %>