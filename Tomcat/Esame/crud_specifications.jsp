<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ include file="config.jsp" %>

<%
    MySqlDbConnection db = new MySqlDbConnection();
    db.setDbUser(dbUser);
    db.setDbPassword(dbPassword);
    db.setDbName(dbName);

    int id = 0;
	String work_note = "0";
	if (request.getParameter("id") != null && request.getParameter("work_note") != null) {
	    id = Integer.parseInt(request.getParameter("id"));
		work_note = request.getParameter("work_note");
    } 

	Specifications wne = new Specifications(db, id);
	wne.setWork_note(work_note);
	wne.update();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<%= wne.getDbOperationMessage() %>